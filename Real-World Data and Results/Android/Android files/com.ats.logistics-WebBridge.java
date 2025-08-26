package com.ats.logistics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.ats.lib.SoapHelper;
import com.ats.lib.Util;
import com.ats.logistics.data.Load;
import com.ats.logistics.data.RequestParams;
import com.ats.logistics.data.RequestUpdateStop;
import com.ats.logistics.data.Stop;
import com.ats.logistics.data.UserSettings;
import com.tms.common.loadmaster.dsp.LoadboardSearchParams;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpsServiceConnectionSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class WebBridge {
    private static final int RETRY_COUNT = 0;
    private static final int RETRY_WAIT = 2000;
    private static TrustManager[] trustManagers;
    public static boolean CLIENT_ONLY_TEST_MODE = false;
    static String NAMESPACE = "http://com.ats.logistics.data";
    public static UserSettings userSettings = new UserSettings();
    private static LoadboardSearchParams loadboardSearchParams = null;
    public static Load[] results = null;
    public static Load selectedLoad = null;
    private static Exception exception = null;
    private static RequestParams requestParams = null;
    public static Stop[] myStops = null;

    public static boolean isException() {
        return exception != null;
    }

    public static void showException(final Context c) {
        final String temp;
        if (exception != null) {
            if (exception instanceof UserFriendlyException) {
                Util.showOkDialog(c, exception.getMessage(), null);
            } else if (exception instanceof UnknownLocationException) {
                UnknownLocationException ule = (UnknownLocationException) exception;
                if (ule.getMessage().toLowerCase().indexOf("destination") != -1) {
                    temp = loadboardSearchParams.getDest();
                } else {
                    temp = loadboardSearchParams.getOrigin();
                }
                AlertDialog.Builder alertbox = new AlertDialog.Builder(c);
                alertbox.setMessage(ule.getMessage());
                alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.ats.logistics.WebBridge.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(c, "Please check spelling and try again.", 0).show();
                    }
                });
                if (temp != null && temp.length() > 0) {
                    alertbox.setNegativeButton("Google it", new DialogInterface.OnClickListener() { // from class: com.ats.logistics.WebBridge.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent search = new Intent("android.intent.action.WEB_SEARCH");
                            search.putExtra("query", temp);
                            c.startActivity(search);
                        }
                    });
                }
                alertbox.show();
            } else if (exception instanceof SocketTimeoutException) {
                AlertDialog.Builder alertbox2 = new AlertDialog.Builder(c);
                alertbox2.setMessage("Our server is currently unavailable.  Would you like to view loads on our website?");
                alertbox2.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.ats.logistics.WebBridge.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface arg0, int arg1) {
                        Util.goToWebUrl(R.string.web_loads, c);
                    }
                });
                alertbox2.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.ats.logistics.WebBridge.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alertbox2.show();
            } else {
                Util.showOkDialog(c, "ERROR:" + exception.getMessage(), null);
            }
        }
    }

    public static void getAvailableLoads(final Handler handler, LoadboardSearchParams inLoadboardSearchParams) {
        loadboardSearchParams = inLoadboardSearchParams;
        new Thread(null, new Runnable() { // from class: com.ats.logistics.WebBridge.5
            @Override // java.lang.Runnable
            public void run() {
                WebBridge.getAvailableLoads(handler, 1);
            }
        }, "MagentoBackground").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getAvailableLoads(Handler handler, int attemptNumber) {
        try {
            results = null;
            selectedLoad = null;
            exception = null;
            if (CLIENT_ONLY_TEST_MODE) {
                results = getTestLoads();
            } else {
                SoapObject request = new SoapObject(NAMESPACE, "getLoads");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;
                envelope.setOutputSoapObject(request);
                envelope.addMapping(NAMESPACE, "com.tms.common.loadmaster.dsp.LoadboardSearchParams", new LoadboardSearchParams().getClass());
                PropertyInfo pi = new PropertyInfo();
                pi.setName("loadboardSearchParams");
                pi.setValue(loadboardSearchParams);
                pi.setType(loadboardSearchParams.getClass());
                request.addProperty(pi);
                HttpsTransportSE androidHttpTransport = getTransport();
                androidHttpTransport.debug = true;
                androidHttpTransport.call(XmlPullParser.NO_NAMESPACE, envelope);
                results = (Load[]) SoapHelper.getObjectsFromSoap(Load.class, SoapHelper.getSoapObjectFromEnvelope(envelope));
                handler.sendEmptyMessage(0);
            }
        } catch (Exception ex) {
            if (!(ex instanceof SocketTimeoutException)) {
                exception = ex;
            } else if (attemptNumber <= 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                getAvailableLoads(handler, attemptNumber + 1);
            } else {
                exception = ex;
            }
        } finally {
            handler.sendEmptyMessage(0);
        }
    }

    private static Load[] getTestLoads() {
        Load l = new Load();
        l.origDist = 10.0d;
        l.destDist = -1.0d;
        l.date = "Jul-18";
        l.loadNumber = "1234567";
        l.origin = "CLEARWATER, MN";
        l.dest = "ROSWELL, NM";
        l.equipment = "F";
        l.contact = "Eric";
        l.info = "THIS IS A GOOD LOAD";
        l.phone = "320-281-9034";
        Load l2 = new Load();
        l2.origDist = 22.0d;
        l2.destDist = -1.0d;
        l2.date = "Jul-25";
        l2.loadNumber = "7654321";
        l2.origin = "ST. CLOUD, MN";
        l2.dest = "LAS VEGAS, NV";
        l2.equipment = "F";
        l2.contact = "Jim-Bob or Tammy";
        l2.info = "Lumber. Must have straps. Great $.";
        l2.phone = "320-281-9034";
        return new Load[]{l, l2};
    }

    public static UserSettings getUserSettings() {
        return userSettings;
    }

    public static void registerDeviceSettings(boolean initial) {
        if (initial) {
            if (Util.isEmptyString(userSettings.getAndroidId())) {
                userSettings.setAndroidId(AtsMain.androidID);
                userSettings.setDevicePhone(AtsMain.devicePhone);
                userSettings.setOsVersion(Build.VERSION.RELEASE);
            } else {
                return;
            }
        }
        SettingsActivity.fillUserSettings(userSettings);
        new Thread() { // from class: com.ats.logistics.WebBridge.6
            int attemptNumber = 0;

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    SoapObject request = new SoapObject(WebBridge.NAMESPACE, "registerDevice");
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = false;
                    envelope.setOutputSoapObject(request);
                    envelope.addMapping(WebBridge.NAMESPACE, "com.ats.logistics.data.UserSettings", new UserSettings().getClass());
                    PropertyInfo pi = new PropertyInfo();
                    pi.setName("userSettings");
                    pi.setValue(WebBridge.userSettings);
                    pi.setType(WebBridge.userSettings.getClass());
                    request.addProperty(pi);
                    HttpsTransportSE androidHttpTransport = WebBridge.getTransport();
                    androidHttpTransport.debug = true;
                    androidHttpTransport.call(XmlPullParser.NO_NAMESPACE, envelope);
                } catch (Exception ex) {
                    if (!(ex instanceof SocketTimeoutException)) {
                        Log.e("registerDevice", "ERROR", ex);
                    } else if (this.attemptNumber <= 0) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        this.attemptNumber++;
                        run();
                    } else {
                        Log.e("registerDevice", "ERROR-SocketTimeoutException", ex);
                    }
                }
            }
        }.start();
    }

    public static void getMyLoads(final Handler handler, RequestParams inRequestParams) {
        requestParams = inRequestParams;
        new Thread(null, new Runnable() { // from class: com.ats.logistics.WebBridge.7
            @Override // java.lang.Runnable
            public void run() {
                WebBridge.getMyLoads(handler, 1);
            }
        }, "MagentoBackground").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getMyLoads(Handler handler, int attemptNumber) {
        try {
            myStops = null;
            exception = null;
            SoapObject request = new SoapObject(NAMESPACE, "getMyLoads");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "com.ats.logistics.data.RequestParams", new RequestParams().getClass());
            PropertyInfo pi = new PropertyInfo();
            pi.setName("requestParams");
            pi.setValue(requestParams);
            pi.setType(requestParams.getClass());
            request.addProperty(pi);
            HttpsTransportSE androidHttpTransport = getTransport();
            androidHttpTransport.debug = true;
            androidHttpTransport.call(XmlPullParser.NO_NAMESPACE, envelope);
            myStops = (Stop[]) SoapHelper.getObjectsFromSoap(Stop.class, SoapHelper.getSoapObjectFromEnvelope(envelope));
        } catch (Exception ex) {
            if (!(ex instanceof SocketTimeoutException)) {
                exception = ex;
            } else if (attemptNumber <= 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                getMyLoads(handler, attemptNumber + 1);
            } else {
                exception = ex;
            }
        } finally {
            handler.sendEmptyMessage(0);
        }
    }

    public static void updateMyLoad(final Handler handler, RequestUpdateStop inRequestParams) {
        requestParams = inRequestParams;
        new Thread(null, new Runnable() { // from class: com.ats.logistics.WebBridge.8
            @Override // java.lang.Runnable
            public void run() {
                WebBridge.updateMyLoad(handler, 1);
            }
        }, "MagentoBackground").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateMyLoad(Handler handler, int attemptNumber) {
        try {
            exception = null;
            SoapObject request = new SoapObject(NAMESPACE, "updateMyLoad");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "com.ats.logistics.data.RequestUpdateStop", new RequestUpdateStop().getClass());
            PropertyInfo pi = new PropertyInfo();
            pi.setName("requestParams");
            pi.setValue(requestParams);
            pi.setType(requestParams.getClass());
            request.addProperty(pi);
            HttpsTransportSE androidHttpTransport = getTransport();
            androidHttpTransport.debug = true;
            androidHttpTransport.call(XmlPullParser.NO_NAMESPACE, envelope);
            Stop[] stops = (Stop[]) SoapHelper.getObjectsFromSoap(Stop.class, SoapHelper.getSoapObjectFromEnvelope(envelope));
            if (stops == null || stops.length <= 0) {
                throw new Exception("stop data not returned from update call");
            }
            Stop stop = stops[0];
            int i = 0;
            while (true) {
                if (i >= myStops.length) {
                    break;
                } else if (stop.stopId.equals(myStops[i].stopId)) {
                    myStops[i] = stop;
                    break;
                } else {
                    i++;
                }
            }
        } catch (Exception ex) {
            if (!(ex instanceof SocketTimeoutException)) {
                exception = ex;
            } else if (attemptNumber <= 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                updateMyLoad(handler, attemptNumber + 1);
            } else {
                exception = ex;
            }
        } finally {
            handler.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HttpsTransportSE getTransport() throws Exception {
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory();
        System.out.println("host: android.ats-inc.com");
        System.out.println("/IS1160s/services/Search");
        HttpsTransportSE transport = new HttpsTransportSE("android.ats-inc.com", 443, "/IS1160s/services/Search", 60000);
        ((HttpsServiceConnectionSE) transport.getServiceConnection()).setSSLSocketFactory(sslSocketFactory);
        transport.debug = true;
        return transport;
    }

    /* loaded from: classes.dex */
    public static class _FakeX509TrustManager implements X509TrustManager {
        private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[0];

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }
    }

    public static SSLSocketFactory getSSLSocketFactory() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.ats.logistics.WebBridge.9
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        SSLContext context = null;
        if (trustManagers == null) {
            trustManagers = new TrustManager[]{new _FakeX509TrustManager()};
        }
        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, trustManagers, new SecureRandom());
        } catch (KeyManagementException e) {
            Log.e("allowAllSSL", e.toString());
        } catch (NoSuchAlgorithmException e2) {
            Log.e("allowAllSSL", e2.toString());
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        return context.getSocketFactory();
    }
}
