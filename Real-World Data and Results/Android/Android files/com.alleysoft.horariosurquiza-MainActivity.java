package com.alleysoft.horariosurquiza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    public static final String ACTION_ALERT = "com.alleysoft.horariosurquiza.ALERT";
    public static final String ACTION_PING = "com.alleysoft.horariosurquiza.PING";
    public static final String ACTION_START = "com.alleysoft.horariosurquiza.START";
    public static final String ACTION_STOP = "com.alleysoft.horariosurquiza.STOP";
    private static final int AHORA = 24;
    private static final int BORRAR_BUSQUEDA = 1;
    private static final int CAUSA_ESTADO = 2;
    public static final String CHANNEL_ID = "horarios";
    private static final int COVID19 = 8;
    public static final String EXTRA_BUSQUEDA = "com.alleysoft.horariosurquiza.BUSQUEDA";
    public static final String EXTRA_ESTACION = "com.alleysoft.horariosurquiza.ESTACION";
    public static final String EXTRA_MENSAJE = "com.alleysoft.horariosurquiza.ALARM_MESSAGE";
    public static final String EXTRA_TIEMPO_ANTES = "com.alleysoft.horariosurquiza.TIEMPO_ANTES";
    public static final String EXTRA_TIEMPO_FINAL = "com.alleysoft.horariosurquiza.TIEMPO_FINAL";
    public static final String EXTRA_TREN = "com.alleysoft.horariosurquiza.TREN";
    private static final int G20 = 5;
    private static final int HABILITAR_GPS = 3;
    private static final int IDEM_ESTACIONES = 4;
    private static final int OBRAS = 7;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 0;
    public static final String SEPARADOR = "&nbsp;&bull;&nbsp;";
    private static final String TAG = "StatusActivity";
    private static final int TRASBORDO = 6;
    private ArrayList<Busqueda> Busquedas;
    private Calendar Fecha;
    private final int MAXBUSQUEDAS = 5;
    private final int MAXHISTORIAL = 30;
    private Spinner cambiarHora;
    private Spinner cambiarTabla;
    private DbHelper dbHelper;
    private Spinner estacionDestino;
    private Spinner estacionOrigen;
    private TextView estadoServicio;
    private EditText frecuenciaActual;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private ScrollView scrollView;
    private ListView ultimasBusquedas;
    private boolean updaterRunning;

    /* loaded from: classes.dex */
    public enum Estaciones {
        LACR("Federico Lacroze", -34.5868d, -58.4553d),
        ARTI("Jose Artigas", -34.59d, -58.4663d),
        ARAT("Pedro N. Arata", -34.5925d, -58.4765d),
        BEIR("Doctor F. Beiro", -34.5931d, -58.4942d),
        LIBE("El Libertador", -34.5946d, -58.5038d),
        DEVO("Antonio Devoto", -34.5955d, -58.5106d),
        LYNC("Coronel F. Lynch", -34.5971d, -58.5244d),
        FEMO("Fernandez Moreno", -34.5955d, -58.5352d),
        LOUR("Lourdes", -34.5937d, -58.5469d),
        TROP("Tropezon", -34.5918d, -58.5593d),
        VIBO("Jose M. Bosch", -34.5899d, -58.5718d),
        MACO("Martin Coronado", -34.5889d, -58.5907d),
        PODE("Pablo Podesta", -34.5884d, -58.607d),
        JONE("Jorge Newbery", -34.5878d, -58.621d),
        RUDA("Ruben Dario", -34.5876d, -58.6295d),
        EDLA("Ejercito de los Andes", -34.58d, -58.6404d),
        LASA("Juan B. de La Salle", -34.5735d, -58.6452d),
        SABA("Sargento Barrufaldi", -34.5627d, -58.6598d),
        CALO("Capitan Lozano", -34.5556d, -58.6694d),
        TEAG("Teniente Agneta", -34.5491d, -58.6784d),
        CDEM("Campo de Mayo", -34.5438d, -58.6857d),
        SACA("Sargento Cabral", -34.5371d, -58.6949d),
        GELE("General Lemos", -34.5332d, -58.7003d);
        
        private Location locacion = new Location("network");
        private final String nombre;

        Estaciones(String estacionNombre, double latitud, double longitud) {
            this.nombre = estacionNombre;
            this.locacion.setLatitude(latitud);
            this.locacion.setLongitude(longitud);
        }

        public String getNombre() {
            return this.nombre;
        }

        public Location getLocacion() {
            return this.locacion;
        }

        public static void addToAdapter(ArrayAdapter<String> adapter) {
            for (Estaciones estacion : values()) {
                adapter.add(estacion.getNombre());
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Tablas {
        LUNAVIE("Lunes a Viernes", "LunesAViernes"),
        SAB("Sabados", "Sabados"),
        DOMYFER("Domingos y Feriados", "DomingosYFeriados");
        
        private final String nombre;
        private final String nombreDB;

        Tablas(String nombre, String nombreDB) {
            this.nombre = nombre;
            this.nombreDB = nombreDB;
        }

        public String getNombre() {
            return this.nombre;
        }

        public String getNombreDB() {
            return this.nombreDB;
        }

        public static void addToAdapter(ArrayAdapter<String> tablas) {
            for (Tablas tabla : values()) {
                tablas.add(tabla.getNombre());
            }
        }

        public static Tablas getTabla(Calendar Fecha) {
            Feriados feriados = new Feriados();
            if (Fecha.get(7) == 1 || feriados.esFeriado(Fecha)) {
                return DOMYFER;
            }
            if (Fecha.get(7) == 7) {
                return SAB;
            }
            return LUNAVIE;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> estaciones = new ArrayAdapter<>(this, 17367048);
        Estaciones.addToAdapter(estaciones);
        estaciones.setDropDownViewResource(17367049);
        ArrayAdapter<String> tablas = new ArrayAdapter<>(this, 17367048);
        Tablas.addToAdapter(tablas);
        tablas.setDropDownViewResource(17367049);
        String[] Horas = new String[25];
        Horas[24] = "Ahora";
        for (int i = 0; i < 24; i++) {
            Horas[i] = String.format("%02d:00", Integer.valueOf(i));
        }
        ArrayAdapter<String> horas = new ArrayAdapter<>(this, 17367048, Horas);
        horas.setDropDownViewResource(17367049);
        this.dbHelper = new DbHelper(this);
        this.scrollView = (ScrollView) findViewById(R.id.scrollView);
        this.estacionOrigen = (Spinner) findViewById(R.id.estacionOrigen);
        this.estacionOrigen.setAdapter((SpinnerAdapter) estaciones);
        this.estacionOrigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                MainActivity.this.establecerFrecuencia();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.estacionDestino = (Spinner) findViewById(R.id.estacionDestino);
        this.estacionDestino.setAdapter((SpinnerAdapter) estaciones);
        this.cambiarTabla = (Spinner) findViewById(R.id.cambiarTabla);
        this.cambiarTabla.setAdapter((SpinnerAdapter) tablas);
        this.cambiarTabla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos != Tablas.getTabla(MainActivity.this.Fecha).ordinal()) {
                    MainActivity.this.cambiarHora.setSelection(Calendar.getInstance().get(11));
                }
                MainActivity.this.establecerFrecuencia();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.cambiarHora = (Spinner) findViewById(R.id.cambiarHora);
        this.cambiarHora.setAdapter((SpinnerAdapter) horas);
        this.cambiarHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 24) {
                    MainActivity.this.establecerDiayHora();
                }
                MainActivity.this.establecerFrecuencia();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        this.estadoServicio = (TextView) findViewById(R.id.estadoServicio);
        this.frecuenciaActual = (EditText) findViewById(R.id.frecuenciaActual);
        this.ultimasBusquedas = (ListView) findViewById(R.id.ultimasBusquedas);
        this.ultimasBusquedas.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MainActivity.this.establecerBusqueda(position);
            }
        });
        this.ultimasBusquedas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.5
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Bundle args = new Bundle();
                args.putInt("position", position);
                MainActivity.this.mostrarDialogo(1, args);
                return true;
            }
        });
        createNotificationChannel();
        iniciarLocacion();
        establecerDiayHora();
        establecerFrecuencia();
        cargarBusquedas();
        establecerBusqueda(0);
        mostrarBusquedas();
        actualizarLocacion();
        deleteDatabase("horarios-urquiza-20131216.db");
        deleteDatabase("horarios-urquiza-20140224.db");
        deleteDatabase("horarios-urquiza-20141215.db");
        deleteDatabase("horarios-urquiza-20151214.db");
        deleteDatabase("horarios-urquiza-20160229.db");
        deleteDatabase("horarios-urquiza-20160229b.db");
        deleteDatabase("horarios-urquiza-20160229c.db");
        deleteDatabase("horarios-urquiza-20161219.db");
        deleteDatabase("horarios-urquiza-20161219b.db");
        deleteDatabase("horarios-urquiza-20170227.db");
        deleteDatabase("horarios-urquiza-20171218.db");
        deleteDatabase("horarios-urquiza-20180226.db");
        deleteDatabase("horarios-urquiza-20200101.db");
        deleteDatabase("horarios-urquiza-20210101.db");
        System.out.println("Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.informacion /* 2131361821 */:
                startActivity(new Intent(this, InfoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        obtenerEstado();
        establecerDiayHora();
        establecerFrecuencia();
        cargarBusquedas();
        establecerBusqueda(0);
        mostrarBusquedas();
        actualizarLocacion();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.locationManager != null && this.locationListener != null) {
            this.locationManager.removeUpdates(this.locationListener);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        System.out.println("onRequestPermissionsResult()");
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0) {
                    int i = grantResults[0];
                    return;
                }
                return;
            default:
                return;
        }
    }

    @SuppressLint({"NewApi"})
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, "default", 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mostrarDialogo(int id, final Bundle args) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        switch (id) {
            case 1:
                alertDialogBuilder.setTitle(R.string.titulo_borrar_entrada).setMessage(R.string.mensaje_borrar_entrada).setPositiveButton(R.string.boton_acceptar, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id2) {
                        int position = args.getInt("position");
                        Log.d(MainActivity.TAG, String.format("position = %d, estOrigen = %d, estDestino = %d", Integer.valueOf(position), Integer.valueOf(((Busqueda) MainActivity.this.Busquedas.get(position)).getOrigen()), Integer.valueOf(((Busqueda) MainActivity.this.Busquedas.get(position)).getDestino())));
                        SQLiteDatabase db = MainActivity.this.dbHelper.getReadableDatabase();
                        db.delete("Historial", "estacionOrigen = ? AND estacionDestino = ?", new String[]{String.format("%d", Integer.valueOf(((Busqueda) MainActivity.this.Busquedas.get(position)).getOrigen())), String.format("%d", Integer.valueOf(((Busqueda) MainActivity.this.Busquedas.get(position)).getDestino()))});
                        db.close();
                        MainActivity.this.Busquedas.remove(position);
                        MainActivity.this.mostrarBusquedas();
                    }
                }).setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id2) {
                        dialog.cancel();
                    }
                });
                break;
            case 2:
                View dialogView = getLayoutInflater().inflate(R.layout.wv_dialog, (ViewGroup) null);
                WebView wv = (WebView) dialogView.findViewById(R.id.web);
                wv.loadUrl("https://aplicacioneswp.metrovias.com.ar/estadolineasemova/desktop.html?v=2");
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setWebChromeClient(new WebChromeClient() { // from class: com.alleysoft.horariosurquiza.MainActivity.8
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                alertDialogBuilder.setView(dialogView);
                break;
            case 3:
                if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    View checkBoxViewGPS = View.inflate(this, R.layout.nm_dialog, null);
                    ((CheckBox) checkBoxViewGPS.findViewById(R.id.dialogoNM)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.9
                        @Override // android.widget.CompoundButton.OnCheckedChangeListener
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            SharedPreferences.Editor editor = MainActivity.this.getPreferences(0).edit();
                            editor.putBoolean("gps_dialog", false);
                            editor.commit();
                        }
                    });
                    alertDialogBuilder.setTitle(R.string.titulo_habilitar_gps).setMessage(R.string.mensaje_habilitar_gps).setView(checkBoxViewGPS).setPositiveButton(R.string.boton_habilitar_gps, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.10
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int id2) {
                            MainActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                        }
                    }).setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.11
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int id2) {
                            dialog.cancel();
                        }
                    });
                    break;
                } else {
                    return;
                }
            case 4:
                alertDialogBuilder.setTitle(R.string.titulo_idem_estaciones).setMessage(R.string.mensaje_idem_estaciones).setPositiveButton(R.string.boton_acceptar, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.12
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id2) {
                    }
                });
                break;
            case 8:
                alertDialogBuilder.setTitle(R.string.titulo_covid19).setMessage(R.string.mensaje_covid19).setPositiveButton(R.string.boton_acceptar, new DialogInterface.OnClickListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.13
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int id2) {
                        if (MainActivity.this.cambiarHora.getSelectedItemPosition() != 24) {
                            MainActivity.this.mostrarHorarios();
                        }
                    }
                });
                break;
        }
        alertDialogBuilder.create().show();
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), 0);
            int totalHeight = 0;
            View view = null;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                view = listAdapter.getView(i, view, listView);
                if (i == 0) {
                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, -2));
                }
                view.measure(desiredWidth, 0);
                totalHeight += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + totalHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    private void iniciarLocacion() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                Toast.makeText(getApplicationContext(), "El permiso de locacion permite encontrar la estacion mas cercana", 0).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
            }
        }
        this.locationManager = (LocationManager) getSystemService("location");
        if (!this.locationManager.isProviderEnabled("gps") && getPreferences(0).getBoolean("gps_dialog", true)) {
            mostrarDialogo(3, null);
        }
        this.locationListener = new LocationListener() { // from class: com.alleysoft.horariosurquiza.MainActivity.14
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                MainActivity.this.makeUseOfNewLocation(location);
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
        };
    }

    private void actualizarLocacion() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && this.locationManager != null) {
            if (this.locationManager.getAllProviders().contains("gps")) {
                this.locationManager.requestLocationUpdates("gps", 60000, 100.0f, this.locationListener);
            }
            if (this.locationManager.getAllProviders().contains("network")) {
                this.locationManager.requestLocationUpdates("network", 60000, 100.0f, this.locationListener);
            }
            Location lastKnownLocation = this.locationManager.getLastKnownLocation("network");
            if (lastKnownLocation != null) {
                makeUseOfNewLocation(lastKnownLocation);
            }
        }
    }

    protected void makeUseOfNewLocation(Location location) {
        ArrayAdapter<String> estaciones = new ArrayAdapter<>(this, 17367048);
        estaciones.setDropDownViewResource(17367049);
        Estaciones cercana = Estaciones.LACR;
        Estaciones[] values = Estaciones.values();
        for (Estaciones estacion : values) {
            double distancia = (double) location.distanceTo(estacion.getLocacion());
            String nombre = estacion.getNombre();
            if (distancia < ((double) location.distanceTo(cercana.getLocacion()))) {
                cercana = estacion;
            }
            if (distancia < 50.0d) {
                estaciones.add(String.format("%s (%d m)", nombre, Integer.valueOf((int) distancia)));
            } else {
                estaciones.add(String.format("%s (%.02f Km)", nombre, Double.valueOf(distancia / 1000.0d)));
            }
        }
        this.estacionOrigen.setAdapter((SpinnerAdapter) estaciones);
        this.estacionOrigen.setSelection(cercana.ordinal());
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor cursorBusquedas = db.rawQuery(String.format(Locale.US, "SELECT estacionOrigen, estacionDestino FROM Historial WHERE estacionOrigen=%d", Integer.valueOf(cercana.ordinal())), null);
        if (cursorBusquedas.moveToNext()) {
            this.estacionDestino.setSelection(cursorBusquedas.getInt(cursorBusquedas.getColumnIndexOrThrow("estacionDestino")));
        }
        cursorBusquedas.close();
        db.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void establecerBusqueda(int position) {
        if (this.Busquedas != null && this.Busquedas.size() != 0) {
            Busqueda busqueda = this.Busquedas.get(position);
            this.estacionOrigen.setSelection(busqueda.getOrigen());
            this.estacionDestino.setSelection(busqueda.getDestino());
        }
    }

    private void cargarBusquedas() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        this.Busquedas = new ArrayList<>();
        db.delete("Historial", "timestamp < ?", new String[]{String.format("%d", Long.valueOf(Calendar.getInstance().getTimeInMillis() - 2592000000L))});
        Cursor cursorBusquedas = db.rawQuery(String.format(Locale.US, "SELECT DISTINCT estacionOrigen, estacionDestino FROM Historial LIMIT %d", 5), null);
        while (cursorBusquedas.moveToNext()) {
            this.Busquedas.add(new Busqueda(cursorBusquedas.getInt(cursorBusquedas.getColumnIndexOrThrow("estacionOrigen")), cursorBusquedas.getInt(cursorBusquedas.getColumnIndexOrThrow("estacionDestino")), Tablas.LUNAVIE, null));
        }
        cursorBusquedas.close();
        db.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mostrarBusquedas() {
        Log.d(TAG, String.format("Busquedas = %d", Integer.valueOf(this.Busquedas.size())));
        ArrayList<Spanned> listaBusquedas = new ArrayList<>();
        if (this.Busquedas.size() != 0) {
            Iterator<Busqueda> it = this.Busquedas.iterator();
            while (it.hasNext()) {
                listaBusquedas.add(it.next().obtenerBusqueda());
            }
        }
        this.ultimasBusquedas.setAdapter((ListAdapter) new ArrayAdapter<>(this, 17367043, listaBusquedas));
        setListViewHeightBasedOnChildren(this.ultimasBusquedas);
        this.scrollView.postDelayed(new Runnable() { // from class: com.alleysoft.horariosurquiza.MainActivity.15
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.scrollView.scrollTo(0, 0);
            }
        }, 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void establecerDiayHora() {
        this.Fecha = Calendar.getInstance();
        this.cambiarHora.setSelection(24);
        this.cambiarTabla.setSelection(Tablas.getTabla(this.Fecha).ordinal());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void establecerFrecuencia() {
        int tabla = this.cambiarTabla.getSelectedItemPosition();
        int hora = this.cambiarHora.getSelectedItemPosition();
        this.frecuenciaActual.setText(((Horarios) getApplication()).obtenerFrecuencia(Tablas.values()[tabla], this.estacionOrigen.getSelectedItemPosition(), this.estacionDestino.getSelectedItemPosition(), hora));
    }

    public void conmutarEstaciones(View v) {
        if (!(this.locationManager == null || this.locationListener == null)) {
            this.locationManager.removeUpdates(this.locationListener);
        }
        int tmp = this.estacionDestino.getSelectedItemPosition();
        this.estacionDestino.setSelection(this.estacionOrigen.getSelectedItemPosition());
        this.estacionOrigen.setSelection(tmp);
    }

    public void causaEstado(View v) {
        Log.d(TAG, "causaEstado(View v)");
        mostrarDialogo(2, null);
    }

    public void imprimirHorarios(View v) {
        mostrarHorarios();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mostrarHorarios() {
        int hora = this.cambiarHora.getSelectedItemPosition();
        int tabla = this.cambiarTabla.getSelectedItemPosition();
        int estOrigen = this.estacionOrigen.getSelectedItemPosition();
        int estDestino = this.estacionDestino.getSelectedItemPosition();
        Calendar fecha = Calendar.getInstance();
        if (hora == 24) {
            fecha = null;
        } else {
            fecha.set(11, hora);
            fecha.set(12, 0);
            fecha.set(13, 0);
            fecha.set(14, 0);
        }
        Busqueda busqueda = new Busqueda(estOrigen, estDestino, Tablas.values()[tabla], fecha);
        if (estOrigen == estDestino) {
            mostrarDialogo(4, null);
            return;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put("estacionOrigen", Integer.valueOf(estOrigen));
        values.put("estacionDestino", Integer.valueOf(estDestino));
        values.put("tabla", Integer.valueOf(tabla));
        values.put("timestamp", Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        db.insert("Historial", null, values);
        Intent intent = new Intent(this, HorariosActivity.class);
        intent.putExtra(EXTRA_BUSQUEDA, busqueda);
        startActivity(intent);
    }

    private void obtenerEstado() {
        if (!this.updaterRunning) {
            Log.d(TAG, "obtenerEstado()");
            NetworkInfo networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_amar_off, 0, 0, 0);
                this.estadoServicio.setText("No hay internet");
                return;
            }
            this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_verde_off, 0, 0, 0);
            this.estadoServicio.setText("Presione aqui para ver el estado");
        }
    }

    /* loaded from: classes.dex */
    private class Updater extends AsyncTask<String, Void, String> {
        private Updater() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String doInBackground(String... urls) {
            MainActivity.this.updaterRunning = true;
            return MainActivity.this.descargarEstado(urls[0]);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onPostExecute(String result) {
            Log.d(MainActivity.TAG, "onPostExecute");
            MainActivity.this.updaterRunning = false;
            if (result.indexOf("semaf_verde") != -1) {
                MainActivity.this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_verde_on, 0, 0, 0);
            } else if (result.indexOf("semaf_amarillo") != -1) {
                MainActivity.this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_amar_on, 0, 0, 0);
            } else if (result.indexOf("semaf_rojo") != -1) {
                MainActivity.this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_rojo_on, 0, 0, 0);
            } else if (result.indexOf("semaf_naranja") != -1) {
                MainActivity.this.estadoServicio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.semaf_amar_off, 0, 0, 0);
            }
            MainActivity.this.estadoServicio.setText(Html.fromHtml("<html>" + result.substring(result.indexOf(62) + 1, result.lastIndexOf(60)) + "</html>"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String descargarEstado(String URL) {
        String[] options = HttpURLConnection(URL).split(",");
        System.out.println(options[1]);
        String[] token = options[1].split(":");
        String tokenID = token[1].substring(1, token[1].length() - 1);
        String URLEncodedTokenID = "";
        try {
            URLEncodedTokenID = URLEncoder.encode(tokenID, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(tokenID);
        System.out.println();
        String url2 = "https://www.metrovias.com.ar/estadolineas/signalr/connect?transport=serverSentEvents&clientProtocol=2.0&connectionToken=" + URLEncodedTokenID + "&connectionData=%5B%7B%22name%22%3A%22moveshape%22%7D%5D";
        System.out.println(url2);
        String result = HttpURLConnection(url2);
        System.out.println(result);
        int ei = result.indexOf("Linea U");
        if (ei == -1) {
            return "<div id=\"estado\" class=\"semaf_naranja\">Fallo al procesar la respuesta del servidor</div>";
        }
        int es = result.indexOf("align-self-center", ei);
        int ef = result.indexOf("</div>", es);
        String estadoOrig = result.substring(es + 19, ef);
        String estado = estadoOrig.toLowerCase(Locale.US);
        Log.d(TAG, String.format("ei = %d, ef = %d", Integer.valueOf(ei), Integer.valueOf(ef)));
        String[] semaf_rojo = {"interru", "medidas de fuerza", "ambulancia", "cerrad"};
        String[] semaf_amarillo = {"limitado", "detiene", "demora"};
        String semaforo = "semaf_verde";
        int i = 0;
        while (true) {
            if (i >= semaf_rojo.length) {
                break;
            } else if (estado.indexOf(semaf_rojo[i]) != -1) {
                semaforo = "semaf_rojo";
                break;
            } else {
                i++;
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= semaf_amarillo.length) {
                break;
            } else if (estado.indexOf(semaf_amarillo[i2]) != -1) {
                semaforo = "semaf_amarillo";
                break;
            } else {
                i2++;
            }
        }
        String estadoOrig2 = "<div id=\"estado\" class=\"" + semaforo + "\">" + estadoOrig + "</div>";
        Log.d(TAG, "Estado: " + estadoOrig2);
        return estadoOrig2;
    }

    private String HttpURLConnection(String URL) {
        String inputLine;
        StringBuffer contenido = new StringBuffer();
        TrustManager[] trustAllCerts = {new X509TrustManager() { // from class: com.alleysoft.horariosurquiza.MainActivity.16
            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trustAllCerts, null);
            HttpsURLConnection conn = (HttpsURLConnection) new URL(URL).openConnection();
            conn.setSSLSocketFactory(context.getSocketFactory());
            conn.setReadTimeout(300000);
            conn.setConnectTimeout(300000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            Log.d(TAG, "The response is: " + conn.getResponseCode());
            InputStream is = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            do {
                inputLine = in.readLine();
                if (inputLine == null) {
                    break;
                }
                contenido.append(inputLine);
            } while (inputLine.indexOf("Linea U") == -1);
            in.close();
            is.close();
            return contenido.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "<div id=\"estado\" class=\"semaf_naranja\">Fallo la conexion al servidor</div>";
        }
    }
}
