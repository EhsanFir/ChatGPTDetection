package com.ampgn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class LoginActivity extends Activity implements LocationListener {
    private static TrustManager[] trustManagers;
    int AppVersion;
    int DOC;
    Button Entrar;
    private String METHOD_NAME;
    private String NAMESPACE;
    String PASS;
    private String SOAP_ACTION;
    Button Salir;
    private String URL;
    String android_id;
    TextView btnConfig;
    ProgressDialog dialog;
    private LocationManager locationManager;
    private String provider;
    EditText tLogin;
    EditText tPassword;
    int networkTimeOut = 60000;
    String Lat = XmlPullParser.NO_NAMESPACE;
    String Long = XmlPullParser.NO_NAMESPACE;
    private VersionChecker mVC = new VersionChecker();
    private Handler handler = new Handler();
    private Runnable finishBackgroundDownload = new Runnable() { // from class: com.ampgn.LoginActivity.1
        @Override // java.lang.Runnable
        public void run() {
            LoginActivity.this.dialog.dismiss();
            if (LoginActivity.this.mVC.isNewVersionAvailable()) {
                Intent i = new Intent(LoginActivity.this, NewVersionActivity.class);
                i.putExtra("descarga", LoginActivity.this.mVC.getDownloadURL());
                LoginActivity.this.startActivityForResult(i, 5);
            }
        }
    };
    private Runnable backgroundDownload = new Runnable() { // from class: com.ampgn.LoginActivity.2
        @Override // java.lang.Runnable
        public void run() {
            LoginActivity.this.mVC.getData(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.OnlineURL));
            LoginActivity.this.handler.post(LoginActivity.this.finishBackgroundDownload);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (VerificaConexion.ValidarConexion(getBaseContext())) {
            this.dialog = ProgressDialog.show(this, XmlPullParser.NO_NAMESPACE, String.valueOf(getString(R.string.login_validando)) + "...", true);
            this.dialog.setContentView(R.layout.custom_progressdialog);
            new Thread(this.backgroundDownload, "VersionChecker").start();
        }
        this.android_id = Settings.Secure.getString(getContentResolver(), "android_id");
        this.Entrar = (Button) findViewById(R.id.btnLoginEntrar);
        this.Salir = (Button) findViewById(R.id.btnLoginSalir);
        this.tLogin = (EditText) findViewById(R.id.txtLoginUser);
        this.tPassword = (EditText) findViewById(R.id.txtLoginPassword);
        this.NAMESPACE = getResources().getString(R.string.ServerNameSpace);
        SharedPreferences preferencias = getSharedPreferences("datos", 0);
        this.URL = getResources().getString(R.string.ServerURL);
        SharedPreferences.Editor editor = preferencias.edit();
        this.AppVersion = getVersion();
        editor.putInt("version", this.AppVersion);
        editor.putString("ServerURL", this.URL);
        editor.commit();
        this.tPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.ampgn.LoginActivity.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != 6) {
                    return false;
                }
                LoginActivity.this.Entrar.performClick();
                return true;
            }
        });
        this.btnConfig = (TextView) findViewById(R.id.LoginFooterConfig);
        this.btnConfig.setOnClickListener(new View.OnClickListener() { // from class: com.ampgn.LoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ConfigServerActivity.class));
            }
        });
    }

    public int getVersion() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 1;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String provider) {
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String provider) {
    }

    public void Enter(View v) throws Exception {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.tPassword.getWindowToken(), 0);
        if (this.tLogin.getText().toString().equals(XmlPullParser.NO_NAMESPACE) || this.tPassword.getText().toString().equals(XmlPullParser.NO_NAMESPACE)) {
            Toast.makeText(getBaseContext(), (int) R.string.login_error, 0).show();
        } else if (VerificaConexion.ValidarConexion(getBaseContext())) {
            ProcessSync();
        } else {
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            d.setTitle(R.string.login_error_conexion_title);
            d.setMessage(R.string.login_error_conexion_message);
            d.setNegativeButton(17039370, new DialogInterface.OnClickListener() { // from class: com.ampgn.LoginActivity.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            d.setIcon(17301543);
            d.show();
            SaveLatLong("C");
        }
    }

    public void ProcessSync() throws Exception {
        this.dialog = ProgressDialog.show(this, XmlPullParser.NO_NAMESPACE, String.valueOf(getString(R.string.login_validando)) + "...", true);
        this.dialog.setContentView(R.layout.custom_progressdialog);
        this.dialog.setCancelable(false);
        new TareaWSConsulta().execute(new String[0]);
    }

    public void finalizar(View v) {
        finish();
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

    public static void allowAllSSL() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.ampgn.LoginActivity.6
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
        } catch (NoSuchAlgorithmException e2) {
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
    }

    /* loaded from: classes.dex */
    public class TareaWSConsulta extends AsyncTask<String, Integer, Boolean> {
        Integer resulado = 0;
        String msg = XmlPullParser.NO_NAMESPACE;

        public TareaWSConsulta() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0336 A[Catch: SocketException -> 0x0458, Exception -> 0x0492, TryCatch #1 {SocketException -> 0x0458, blocks: (B:3:0x005e, B:4:0x0116, B:6:0x014f, B:11:0x017d, B:13:0x01a1, B:14:0x01b8, B:16:0x01c8, B:17:0x01df, B:18:0x022d, B:20:0x02c9, B:21:0x02cf, B:23:0x0336, B:24:0x03ad, B:26:0x03c1, B:29:0x0414, B:30:0x0422), top: B:38:0x005e }] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x03c1 A[Catch: SocketException -> 0x0458, Exception -> 0x0492, TRY_LEAVE, TryCatch #1 {SocketException -> 0x0458, blocks: (B:3:0x005e, B:4:0x0116, B:6:0x014f, B:11:0x017d, B:13:0x01a1, B:14:0x01b8, B:16:0x01c8, B:17:0x01df, B:18:0x022d, B:20:0x02c9, B:21:0x02cf, B:23:0x0336, B:24:0x03ad, B:26:0x03c1, B:29:0x0414, B:30:0x0422), top: B:38:0x005e }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0422 A[Catch: SocketException -> 0x0458, Exception -> 0x0492, TRY_ENTER, TRY_LEAVE, TryCatch #1 {SocketException -> 0x0458, blocks: (B:3:0x005e, B:4:0x0116, B:6:0x014f, B:11:0x017d, B:13:0x01a1, B:14:0x01b8, B:16:0x01c8, B:17:0x01df, B:18:0x022d, B:20:0x02c9, B:21:0x02cf, B:23:0x0336, B:24:0x03ad, B:26:0x03c1, B:29:0x0414, B:30:0x0422), top: B:38:0x005e }] */
        /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Boolean doInBackground(java.lang.String... r34) {
            /*
                Method dump skipped, instructions count: 1232
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ampgn.LoginActivity.TareaWSConsulta.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(Boolean result) {
            if (result.booleanValue()) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(LoginActivity.this, "AMPGN", null, LoginActivity.this.AppVersion);
                admin.getWritableDatabase();
                SQLiteDatabase bd = admin.getWritableDatabase();
                bd.delete("location", XmlPullParser.NO_NAMESPACE, null);
                bd.close();
                if (this.resulado.intValue() == 0) {
                    LoginActivity.this.dialog.dismiss();
                    LoginActivity.this.tPassword.setText(XmlPullParser.NO_NAMESPACE);
                    Toast.makeText(LoginActivity.this.getBaseContext(), LoginActivity.this.getString(R.string.login_error_user), 0).show();
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    LoginActivity.this.finish();
                    return;
                }
                SharedPreferences.Editor editor = LoginActivity.this.getSharedPreferences("datos", 0).edit();
                editor.putInt("DOC", LoginActivity.this.DOC);
                editor.putString("PASS", LoginActivity.this.PASS);
                editor.commit();
                LoginActivity.this.dialog.dismiss();
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, IndexActivity.class));
                LoginActivity.this.finish();
                return;
            }
            Toast.makeText(LoginActivity.this.getBaseContext(), "El servicio no se encuentra disponible por el momento", 1).show();
            LoginActivity.this.dialog.dismiss();
            LoginActivity.this.SaveLatLong("E");
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void SaveLatLong(String estado) {
        try {
            if (this.Lat != XmlPullParser.NO_NAMESPACE && this.Long != XmlPullParser.NO_NAMESPACE) {
                DateFormat dfgmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                dfgmt.setTimeZone(TimeZone.getTimeZone("GMT"));
                String gmtTime = dfgmt.format(new Date());
                SQLiteDatabase bd = new AdminSQLiteOpenHelper(this, "AMPGN", null, this.AppVersion).getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("fecha", gmtTime);
                registro.put("id", this.android_id);
                registro.put("lat", this.Lat);
                registro.put("lon", this.Long);
                registro.put("estado", estado);
                bd.insert("location", null, registro);
                bd.close();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
            if (resultCode == 0) {
                Toast.makeText(getBaseContext(), "Actualice la versión para ejecutar la aplicación", 1).show();
            }
            finish();
        }
    }
}
