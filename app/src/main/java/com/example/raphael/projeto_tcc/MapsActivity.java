package com.example.raphael.projeto_tcc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.raphael.projeto_tcc.bancoDados.BD;
import com.example.raphael.projeto_tcc.bancoDados.ListUsersActivity;
import com.example.raphael.projeto_tcc.bancoDados.OringemDestino;
import com.example.raphael.projeto_tcc.embarqueDesembarque.EmbarqueDesembarque;
import com.example.raphael.projeto_tcc.linhas.ConsumirJsonLinhas;
import com.example.raphael.projeto_tcc.linhas.LinhaLatLon;
import com.example.raphael.projeto_tcc.onibus.Onibus;
import com.example.raphael.projeto_tcc.pontosLinhas.PontosLinhas;
import com.example.raphael.projeto_tcc.socket.SocketTask;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements GoogleMap.InfoWindowAdapter, DirectionCallback {

    private static String codLinhaMapa;
    private static String prefixoBus;
    private static String latBus;
    private static String lonBus;
    private static Double latOnibus;
    private static Double lonOnibus;
    private static Double latitudeDestino;
    private static Double longitudeDestino;
    private static Double latitudeOrigem;
    private static Double longitudeOrigem;
    private static String horaBus;
    private static String todasLinhas;
    private static Address enderecoOrigem;
    private static Address endercoDestino;
    private AlertDialog alerta;
    private SocketTask st;
    private static String SERVICE_URL = "http://transporteservico.urbs.curitiba.pr.gov.br/getPontosLinha.php?linha=" + codLinhaMapa + "&c=3525b";
    private static final String LOG_TAG = "TCC";
    private SupportMapFragment mapFrag;
    private GoogleMap map;
    private Marker marker;
    private View markerInfo;
    private Polyline polyline;
    private List<LatLng> list = new ArrayList<>();
    private List<LatLng> listPoints = new ArrayList<LatLng>();
    private String distance;
    private String duration;
    private String trajetoTransit;
    private CordenadasLinhas cordenadasLinhas;

    private List<LinhaLatLon> linhaLatLon;
    private List<Object[]> markerOptions;
    private Location location;
    //variáveis que usaremos durante o processo
    private EditText origin;
    private EditText destination;
    private EditText edLatitude;
    private EditText edLongitude;
    private EditText edLatitudeDestino;
    private EditText edLongitudeDestino;
    private EditText edLatitudeOrigem;
    private EditText edLongitudeOrigem;
    private Double latitudePosicao;
    private Double longitudePosicao;
    private String latLng;
    private String origem;
    private Button btLocalizar;
    CameraPosition cameraPosition;
    private Onibus onibus;
    private String serverKey = "AIzaSyCzwJS31I9SQnmybDBGkxyvU6UMqPHHFfg";
    private Button btnRequestDirection;
    private OringemDestino origemDestino = new OringemDestino();
    private EmbarqueDesembarque embarqueDesembarque = new EmbarqueDesembarque();
    private String resultEmbarqueDesembarque;
    private String resultMeioTransporte;
    String radioEmbDesbarque = embarqueDesembarque.resultMeioTransporte;
    String radioMeioTransp = embarqueDesembarque.resultMeioTransporte;
    private String[] meioTra;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    private double distance1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        if (codLinhaMapa != null) {
            codLinhaMapa = this.getIntent().getStringExtra("linhaCodigoMapa");
        } else {
            codLinhaMapa = "0";
        }

        SERVICE_URL = ("http://transporteservico.urbs.curitiba.pr.gov.br/getPontosLinha.php?linha=" + codLinhaMapa + "&c=3525b");
        GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);
        setUpMapIfNeeded();

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        map = mapFrag.getMap();

        DownloadJsonAsyncTask downloadJsonAsyncTask = new DownloadJsonAsyncTask();

        downloadJsonAsyncTask
                .execute("http://transporteservico.urbs.curitiba.pr.gov.br/getShapeLinha.php?linha=" + codLinhaMapa + "&c=3525b");

        setupElements();

        btnRequestDirection = (Button) findViewById(R.id.btn_request_direction);

// Banco de Dados
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                origemDestino.setId((int) bundle.getLong("id"));
                origemDestino.setDescricaoOrigem(bundle.getString("descricaoOrigem"));
                origemDestino.setDescricaoDestino(bundle.getString("descricaoDestino"));
                origemDestino.setLatOrigem(bundle.getString("latOrigem"));
                origemDestino.setLonOrigem(bundle.getString("lonOrigem"));
                origemDestino.setLatDestino(bundle.getString("latDestino"));
                origemDestino.setLonDestino(bundle.getString("lonDestino"));


                origin.setText(origemDestino.getDescricaoOrigem());
                destination.setText(origemDestino.getDescricaoDestino());
                edLatitude.setText(origemDestino.getLatOrigem());
                edLongitude.setText(origemDestino.getLonOrigem());
                edLatitudeDestino.setText(origemDestino.getLatDestino());
                edLongitudeDestino.setText(origemDestino.getLonDestino());

            }
        }
    }

    public void salvarOrigemDestino(View view) {
        origemDestino.setDescricaoOrigem(origin.getText().toString());
        origemDestino.setDescricaoDestino(destination.getText().toString());
        origemDestino.setLatOrigem(edLatitude.getText().toString());
        origemDestino.setLonOrigem(edLongitude.getText().toString());
        origemDestino.setLatDestino(edLatitudeDestino.getText().toString());
        origemDestino.setLonDestino(edLongitudeDestino.getText().toString());

        BD bd = new BD(this);
        bd.inserir(origemDestino);

        Toast.makeText(this, "OrigemDestino inserido com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void editarOrigemDestino(View view) {
        origemDestino.setDescricaoOrigem(origin.getText().toString());
        origemDestino.setDescricaoDestino(destination.getText().toString());
        origemDestino.setLatOrigem(edLatitude.getText().toString());
        origemDestino.setLonOrigem(edLongitude.getText().toString());
        origemDestino.setLatDestino(edLatitudeDestino.getText().toString());
        origemDestino.setLonDestino(edLongitudeDestino.getText().toString());

        BD bd = new BD(this);
        bd.atualizar(origemDestino);

        Toast.makeText(this, "Origem \"" + origemDestino.getOrigem() + "\" atuailizado com sucesso.", Toast.LENGTH_SHORT).show();
    }

    // Método usado para importar os elementos da classe R
    public void setupElements() {
        origin = (EditText) findViewById(R.id.origin);
        destination = (EditText) findViewById(R.id.destination);
        edLatitude = (EditText) findViewById(R.id.edLatitude);
        edLongitude = (EditText) findViewById(R.id.edLongitude);
        edLatitudeDestino = (EditText) findViewById(R.id.edLatitudeDestino);
        edLongitudeDestino = (EditText) findViewById(R.id.edLongitudeDestino);

        try {
            latitudePosicao = new Double(edLatitude.getText().toString());
        } catch (NumberFormatException e) {
            latitudePosicao = Double.valueOf(0);
        }

        try {
            longitudePosicao = new Double(edLongitude.getText().toString());
        } catch (NumberFormatException e) {
            longitudePosicao = Double.valueOf(0);
        }

        btLocalizar = (Button) findViewById(R.id.btLocalizar);
        btLocalizar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startGPS();
                configMap();
                getLocation();
                if (marker != null) {
                    marker.remove();
                }
                list.add(new LatLng(latitudePosicao, longitudePosicao));

                try {
                    enderecoOrigem = buscarEndereco(latitudePosicao, longitudePosicao);
                    origin.setText(enderecoOrigem.getThoroughfare() + " " +
                            enderecoOrigem.getLocality());
//                    + " "
//                            + enderecoOrigem.getAdminArea()

                } catch (IOException e) {
                    Log.i("GPS", e.getMessage());
                }

                //    drawRoute();
            }
        });

    }

    //Método que faz a leitura de fato dos valores recebidos do GPS
    public void startGPS() {
        LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener lListener = new LocationListener() {
            public void onLocationChanged(Location locat) {
                updateView(locat);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, lListener);
    }

    //  Método que faz a atualização da tela para o usuário.
    public void updateView(Location locat) {
        Double latitude = locat.getLatitude();
        Double longitude = locat.getLongitude();

        edLongitude.setText(latitude.toString());
        edLongitude.setText(longitude.toString());

        latitudePosicao = latitude;
        longitudePosicao = longitude;

    }

    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
    private String myLocation;

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();

    }

    public void configMap() {
        map = mapFrag.getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        list = new ArrayList<LatLng>();


        LatLng latLng = new LatLng(latitudePosicao, longitudePosicao);
        cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).bearing(0).tilt(85).build();

        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);

        map.moveCamera(update);
        map.animateCamera(update, 1500, new GoogleMap.CancelableCallback() {
            @Override
            public void onCancel() {
                Log.i("Script", "Cancelado");
            }

            @Override
            public void onFinish() {
                Log.i("Script", "Finalizado");
            }
        });

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoContents(Marker marker) {
                TextView tv = new TextView(MapsActivity.this);
                tv.setText(Html.fromHtml("<b><font color=\"#ff0000\">" + marker.getTitle() + ":</font></b> " + marker.getSnippet()));

                return tv;
            }

            @Override
            public View getInfoWindow(Marker marker) {
                LinearLayout ll = new LinearLayout(MapsActivity.this);
                ll.setPadding(20, 20, 20, 20);
                ll.setBackgroundColor(Color.WHITE);

                TextView tv = new TextView(MapsActivity.this);
                tv.setText(Html.fromHtml("<b><font color=\"#ffffff\">" + marker.getTitle() + ":</font></b> " + marker.getSnippet()));
                ll.addView(tv);

                Button bt = new Button(MapsActivity.this);
                Button bt2 = new Button(MapsActivity.this);
                bt.setText("EMBARQUE");
                bt2.setText("DESEMBARQUE");


                bt.setBackgroundColor(Color.CYAN);
                bt2.setBackgroundColor(Color.BLUE);
                bt.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Log.i("Script", "Botão clicado");
                    }

                });

                ll.addView(bt);
                ll.addView(bt2);
                return ll;
            }
        });


        // EVENTS
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.i("Script", "setOnCameraChangeListener()");

                if (marker != null) {
                    marker.remove();
                }
               // customAddMarker(new LatLng(latitudePosicao, longitudePosicao), "1: Marcador Alterado", "O Marcador foi reposicionado testeeee");

            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("Script", "3: Marker: " + marker.getTitle());
                return false;
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Intent intent = new Intent(MapsActivity.this, EmbarqueDesembarque.class);
                startActivity(intent);

            }
        });
    }

    /**
     * Metodo para retornar o endereço origem
     */
    public Address buscarEndereco(double latitudePosicao, double longitudePosicao)
            throws IOException {
        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;

        geocoder = new Geocoder(getApplicationContext());

        addresses = geocoder.getFromLocation(latitudePosicao, longitudePosicao, 1);
        if (addresses.size() > 0) {
            address = addresses.get(0);
        }
        return address;
    }

    public void customAddMarkerOrigemDigitada(LatLng latLng, String title, String snippet) {

        MarkerOptions options = new MarkerOptions();
        list = new ArrayList<LatLng>();
        options.position(latLng).title(title).snippet(snippet).draggable(true);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.origem));

        marker = map.addMarker(options);

    }

    public void customAddMarkerPosicaoOrigem(String origin2) throws IOException {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double
        List<Address> addresses = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
        addresses = geoCoder.getFromLocationName(origin2, 1);// o numero um aqui é a quantidade maxima de resultados que vc quer receber

        latitudePosicao = addresses.get(0).getLatitude();
        longitudePosicao = addresses.get(0).getLongitude();

        try {
            enderecoOrigem = buscarEndereco(latitudePosicao, longitudePosicao);
            origin.setText(enderecoOrigem.getThoroughfare() + " " + enderecoOrigem.getLocality());

        } catch (IOException e) {
            Log.i("GPS", e.getMessage());
        }

        MarkerOptions options = new MarkerOptions();
        list = new ArrayList<>();


        options.position(new LatLng(Double.parseDouble(String.valueOf(latitudePosicao)), Double.parseDouble(String.valueOf(longitudePosicao))));
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.origem));


        //https://design.google.com/icons/

        //  https://www.iconfinder.com/search/?q=point+bus&price=free
        marker = map.addMarker(options);

        //Posicionar a Camera.
        LatLng latLng = new LatLng(latitudePosicao, longitudePosicao);
        cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).bearing(0).tilt(85).build();

        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);

        map.moveCamera(update);
        map.animateCamera(update, 1500, new GoogleMap.CancelableCallback() {
            @Override
            public void onCancel() {
                Log.i("Script", "Cancelado");
            }

            @Override
            public void onFinish() {
                Log.i("Script", "Finalizado");
            }

        });
    }

    public void customAddMarkerPosicao(String destination) throws IOException {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double
        List<Address> addresses = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
        addresses = geoCoder.getFromLocationName(destination, 1);// o numero um aqui é a quantidade maxima de resultados que vc quer receber
        latitudeDestino = addresses.get(0).getLatitude();
        longitudeDestino = addresses.get(0).getLongitude();


        edLatitudeDestino.setText(latitudeDestino.toString());
        edLongitudeDestino.setText(longitudeDestino.toString());

        Log.i("Cordenadas a partir de Endereco: ",
                "latitude" + latitudeDestino +
                        "Longitude" + longitudeDestino);


        MarkerOptions options = new MarkerOptions();
        list = new ArrayList<>();
        // list.add(new LatLng(Double.parseDouble(origin), Double.parseDouble(destination)));

        options.position(new LatLng(Double.parseDouble(String.valueOf(latitudeDestino)), Double.parseDouble(String.valueOf(longitudeDestino))));
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.destino));

        //https://design.google.com/icons/

        //  https://www.iconfinder.com/search/?q=point+bus&price=free
        marker = map.addMarker(options);


    }
    //testes com google direction

    public void requestDirection(String origin2, String destination2) throws IOException {

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double


        List<Address> directionOrigem = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
        directionOrigem = geoCoder.getFromLocationName(origin2, 1);// o numero um aqui é a quantidade maxima de resultados que vc quer receber
        latitudeOrigem = directionOrigem.get(0).getLatitude();
        longitudeOrigem = directionOrigem.get(0).getLongitude();

        List<Address> directionDestino = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
        directionDestino = geoCoder.getFromLocationName(destination2, 1);
        latitudeDestino = directionDestino.get(0).getLatitude();
        longitudeDestino = directionDestino.get(0).getLongitude();

        try {
            endercoDestino = buscarEndereco(latitudeDestino, longitudeDestino);
            destination.setText(endercoDestino.getThoroughfare() + " " +
                    endercoDestino.getLocality());
            // " " +endercoDestino.getAdminArea()

        } catch (IOException e) {
            Log.i("GPS", e.getMessage());
        }


        GoogleDirection.withServerKey(serverKey)
                .from(new LatLng(Double.parseDouble(String.valueOf(latitudeOrigem)), Double.parseDouble(String.valueOf(longitudeOrigem))))
                        //   .from(new LatLng(-25.5625432, -49.3373152))
                .to(new LatLng(Double.parseDouble(String.valueOf(latitudeDestino)), Double.parseDouble(String.valueOf(longitudeDestino))))
                        //  .to(new LatLng(-25.4844846, -49.29600689999999))
                .transportMode(TransportMode.TRANSIT)
                .alternativeRoute(true)
                .execute(this);

    }

    //@Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getSectionPoint();
            //   ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            for (LatLng position : sectionPositionList) {
                map.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pontoroxo))
                        .title("Escolha EMBARQUE ou DESEMBARQUE")
                        .position(position));
                map.setInfoWindowAdapter(this);
                map.setOnMyLocationChangeListener(null);

            }

            List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
            ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
            for (PolylineOptions polylineOption : polylineOptionList) {
                map.addPolyline(polylineOption);
            }

//            List<Step> stepTravelMode = (List<Step>) direction.getRouteList().get(0).getLegList().get(0).getStepList().get(0).getTransitDetail().getLine();
//            ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
//            for (PolylineOptions polylineOption : polylineOptionList) {
//                map.addPolyline(polylineOption);
//            }

            Step step = new Step();

//            Info distanceInfo = step.getDistance();
//            Info durationInfo = step.getDuration();
//            String distance = distanceInfo.getText();
//            String duration = durationInfo.getText();
//
//            Log.i("Duração Router -->>  : ", duration);
//            TransitDetail transitDetail = step.getTransitDetail();
//            StopPoint arrivalStopPoint = transitDetail.getArrivalStopPoint();
//            StopPoint departureStopPoint = transitDetail.getDepartureStopPoint();
//            TimeInfo arriveTimeInfo = transitDetail.getArrivalTime();
//            TimeInfo departureTimeInfo = transitDetail.getDepartureTime();
//            String headSign = transitDetail.getHeadsign();
//            String stopNumber = transitDetail.getStopNumber();
//            Line transitLine = transitDetail.getLine();
//            Log.i("Linhas Google Direction ---->>  : ", transitLine.toString());
        }
    }

    //    @Override
    public void onDirectionFailure(Throwable t) {
        // Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
//    public void addMarkerOnibus(String destination) throws IOException {
//        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double
//        List<Address> addresses = null;//este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
//        addresses = geoCoder.getFromLocationName(destination, 1);// o numero um aqui é a quantidade maxima de resultados que vc quer receber
////        latitudeDestino = addresses.get(0).getLatitude();
////        longitudeDestino = addresses.get(0).getLongitude();
//
//
//        MarkerOptions options = new MarkerOptions();
//        list = new ArrayList<>();
//        // list.add(new LatLng(Double.parseDouble(origin), Double.parseDouble(destination)));
//
//        options.position(new LatLng(Double.parseDouble(String.valueOf(latitudeOnibus)), Double.parseDouble(String.valueOf(longitudeOnibus))));
//        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
//
//        //https://design.google.com/icons/
//
//        //  https://www.iconfinder.com/search/?q=point+bus&price=free
//        marker = map.addMarker(options);}
//

    public void drawRoute() {
        PolylineOptions po;

        if (polyline == null) {
            po = new PolylineOptions();

            for (int i = 0, tam = list.size(); i < tam; i++) {
                po.add(list.get(i));
            }

            po.color(Color.BLACK).width(4);

            polyline = map.addPolyline(po);
        } else {
            polyline.setPoints(list);
        }
    }

    public void drawRoute2() {
        PolylineOptions po;

        if (polyline == null) {
            po = new PolylineOptions();

            for (int i = 0, tam = list.size(); i < tam; i++) {
                po.add(list.get(i));
            }

            po.color(Color.BLUE).width(4);

            polyline = map.addPolyline(po);
        } else {
            polyline.setPoints(list);
        }
    }

    public void getDistance(View view) {
        distance1 = 0;

        for (int i = 0, tam = list.size(); i < tam; i++) {
            if (i < tam - 1) {
                distance1 += distance(list.get(i), list.get(i + 1));
            }
        }

        Toast.makeText(MapsActivity.this, "Distancia: " + distance1 + " metros", Toast.LENGTH_LONG).show();
    }

    public static double distance(LatLng StartP, LatLng EndP) {
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return 6366000 * c;
    }

    public void getLocation() {

        startGPS();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        latitudePosicao = location.getLatitude();
        longitudePosicao = location.getLongitude();

    }

	/* ***************************************** ROTA ***************************************** */


    public void getRouteByGMAV2(View view) throws IOException {

        edLongitude.setText(longitudePosicao.toString());
        edLatitude = (EditText) findViewById(R.id.edLatitude);
        edLongitude = (EditText) findViewById(R.id.edLongitude);
        edLatitude = (EditText) findViewById(R.id.origin);
        EditText etD = (EditText) findViewById(R.id.destination);
        EditText etO = (EditText) findViewById(R.id.origin);
        String origin = URLEncoder.encode(edLatitude.getText().toString() + edLongitude.getText().toString(), "UTF-8");

        String origin2 = URLEncoder.encode(etO.getText().toString(), "UTF-8");
        String destination2 = URLEncoder.encode(etD.getText().toString(), "UTF-8");

        getRoute(origin2, destination2);

        customAddMarkerPosicao(destination2);
        customAddMarkerOrigemDigitada(new LatLng(latitudePosicao, longitudePosicao), "posição do Local", "O Marcador foi reposicionado");
        customAddMarkerPosicaoOrigem(origin2);
        requestDirection(origin2, destination2);
        OringemDestino oringemDestino = new OringemDestino();
        oringemDestino.setOrigem(edLatitude.getText().toString() + edLongitude.getText().toString());
        oringemDestino.setDestino(etD.getText().toString());

        socketDadosTrajeto();
        oringemDestino.setOrigem(origin);
        oringemDestino.setDestino(destination2);

        String json = generateJSON(oringemDestino);

        callServer("setOrigemDestino", json);
        origem = origin;

    }

//    public void getJson(View view) {
//        callServer("GetJson", "");
//    }


    private OringemDestino degenerateJSON(String data) {
        OringemDestino oringemDestino = new OringemDestino();

        try {
            JSONObject jo = new JSONObject(data);
            JSONArray ja;

            oringemDestino.setOrigem(jo.getString("origem"));
            oringemDestino.setDestino(jo.getString("destino"));


            jo.put("origem", oringemDestino.getOrigem());
            jo.put("destino", oringemDestino.getDestino());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (oringemDestino);
    }

    public String generateJSON(OringemDestino oringemDestino) {
        JSONObject jo = new JSONObject();

        try {
            jo.put("origem", oringemDestino.getOrigem());
            jo.put("destino", oringemDestino.getDestino());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("--------> JsonTostring: ",
                "json=" + jo.toString());

        return (jo.toString());

    }

    private void callServer(final String method, final String data) {
        new Thread() {

            public void run() {
                String answer = HttpConnection.getSetDataWeb("https://app.jelasticlw.com.br/tcc/getOrigemDestino", method, data);

                //  Log.i("Script --->>>", "ANSWER--->>: "+answer);

                if (data.isEmpty()) {
                    degenerateJSON(answer);
                }
            }
        }.start();

    }

    private void assertTrue(boolean b) {
        throw new RuntimeException("Stub!");
    }


    // WEB CONNECTION
    public void getRoute(final String origin2, final String destination2) {
        new Thread() {
            public void run() {
                /** String url = "http://maps.googleapis.com/maps/api/directions/json?origin="
                 + origin + "&destination="
                 + destination + "&sensor=false";**/
                final String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
                        + origin2 + "&destination="
                        + destination2 + "&alternatives=true&mode=transit&key=AIzaSyDBfNS0qRnfz1bEa9CLXhyEvTpNk-xZBno";
                HttpResponse response;
                final HttpGet request;
                AndroidHttpClient client = AndroidHttpClient.newInstance("route");

                request = new HttpGet(url);
                try {
                    response = client.execute(request);
                    final String answer = EntityUtils.toString(response.getEntity());

                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                Log.i("resposta Json---->>", answer);
                                list = buildJSONRoute(answer);
                                callServer(answer, url);
                                drawRoute2();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //    // PARSER JSON
    public List<LatLng> buildJSONRoute(String json) throws JSONException {
        JSONObject result = new JSONObject(json);
        JSONArray routes = result.getJSONArray("routes");


        distance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getString("text");
        duration = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("duration").getString("text");


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Toast.makeText(MapsActivity.this, "Distância" + distance, Toast.LENGTH_SHORT).show();
        Toast.makeText(MapsActivity.this, "Tempo do trajeto : " + duration, Toast.LENGTH_SHORT).show();


        JSONArray steps = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        List<LatLng> lines = new ArrayList<LatLng>();


        for (int i = 0; i < steps.length(); i++) {
            JSONObject Jasonobject = steps.getJSONObject(i);

            String htmlInstrutions = steps.getJSONObject(i).getString("html_instructions");
            Toast.makeText(MapsActivity.this, "Instruções do trajeto : " + htmlInstrutions, Toast.LENGTH_SHORT).show();

            String polyline = steps.getJSONObject(i).getJSONObject("start_location").getString("points");

            for (LatLng p : decodePolyline(polyline)) {
                lines.add(p);
            }
        }




        return (lines);

    }

    // DECODE POLYLINE
    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
            listPoints.add(p);
            //customAddMarkerPoints(new LatLng(p.latitude, p.longitude));
        }
        return listPoints;

    }
    public void customAddMarkerPoints(LatLng latLng) {

        MarkerOptions options = new MarkerOptions();
        listPoints = new ArrayList<LatLng>();
        options.position(latLng).draggable(true);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ponto));
        //https://www.iconfinder.com/search/?q=location&price=free
        //https://design.google.com/icons/

        marker = map.addMarker(options);

    }

    public void getRouteTeste(List<LinhaLatLon> linhaLatLon) {
        list = new ArrayList<>();
        for (LinhaLatLon p : linhaLatLon) {
            list.add(new LatLng(Double.valueOf(p.getLat().replace(",", ".")).doubleValue(), Double.valueOf(p.getLon().replace(",", ".")).doubleValue()));
        }
        PolylineOptions po = new PolylineOptions();

        for (int i = 0, tam = list.size(); i < tam; i++) {
            po.add(list.get(i));
        }

        po.color(Color.BLACK).width(5);
        polyline = map.addPolyline(po);
    }

    public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<LinhaLatLon>> {
        ProgressDialog dialog;

        List<LinhaLatLon> linhasLatLon = new ArrayList<LinhaLatLon>();
        List<PontosLinhas> pontosDasLinhas = new ArrayList<PontosLinhas>();
        List<Onibus> onibuses = new ArrayList<Onibus>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog = ProgressDialog.show(MapsActivity.this, "Aguarde",
//                    "Fazendo download dos dados");
        }

        //Acessa o serviço do JSON e retorna a lista de linhas
        @Override
        protected List<LinhaLatLon> doInBackground(String... params) {
            String urlString = params[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);
            try {
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String json = getStringFromInputStream(instream);
                    instream.close();
                    List<LinhaLatLon> linhasLatLon = getLinhasLatLon(json);
                    return linhasLatLon;
                }
            } catch (Exception e) {
                Log.e("Erro", "Falha ao acessar Web service", e);
            }
            return linhasLatLon;
        }

        //Depois deexecutada a chamada do serviço
        @Override
        protected void onPostExecute(List<LinhaLatLon> result) {
            super.onPostExecute(result);
          //  dialog.dismiss();
            if (result.size() > 0) {


                ArrayAdapter<LinhaLatLon> adapter = new ArrayAdapter<LinhaLatLon>(
                        MapsActivity.this,
                        android.R.layout.simple_list_item_1, result);
                MapsActivity.this.getRouteTeste(result);

            }
        }

        //Retorna uma lista de cordenadas com as informações retornadas do JSON
        public List<LinhaLatLon> getLinhasLatLon(String jsonString) {
            try {
                JSONArray linhaLatLonJson = new JSONArray(jsonString);
                JSONObject linhaLatLon;


                for (int i = 0; i < linhaLatLonJson.length(); i++) {
                    linhaLatLon = new JSONObject(linhaLatLonJson.getString(i));
                    Log.i("Cordenada ENCONTRADA: ",
                            "LAT=" + linhaLatLon.getString("LAT") +
                                    "LON=" + linhaLatLon.getString("LON")

                    );

                    LinhaLatLon objetoLinhaLatLon = new LinhaLatLon();
                    objetoLinhaLatLon.setLat(linhaLatLon.getString("LAT"));
                    objetoLinhaLatLon.setLon(linhaLatLon.getString("LON"));
                    // aqui é o caminhooo


                    linhasLatLon.add(objetoLinhaLatLon);


                }
            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }

            return linhasLatLon;

        }

        //Converte objeto InputStream para String
        private String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();

        }

        public List<LinhaLatLon> getLinhasLatLon() {
            return linhasLatLon;
        }

    }

    /**
     * Métodos para desenhar os pontos Markers
     */
    private void setUpMapIfNeeded() {
        if (map == null) {
            mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);


            map = mapFrag.getMap();
            if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (codLinhaMapa != null) {
                        buscaERetornaJson();
                        buscaERetornaJson2();
                    }

                } catch (IOException e) {
                    Log.e(LOG_TAG, "Cannot retrive cities", e);
                    return;
                }
            }
        }).start();
    }

    protected void buscaERetornaJson() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // conectra com o web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());


            // Le os dados JSON para o StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro na conexão com o service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        // Cria os marcadores dos pontos.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    createMarkersFromJson(json.toString());

                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error processing JSON", e);
                }
            }
        });
    }

    protected void buscaERetornaJson2() throws IOException {

        HttpURLConnection conn2 = null;
        final StringBuilder json2 = new StringBuilder();
        try {

            URL url2 = new URL("http://transporteservico.urbs.curitiba.pr.gov.br/getVeiculosLinha.php?linha=" + codLinhaMapa + "&c=3525b");


            conn2 = (HttpURLConnection) url2.openConnection();
            InputStreamReader in2 = new InputStreamReader(conn2.getInputStream());

            // Le os dados JSON para o StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in2.read(buff)) != -1) {
                json2.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro na conexão com o service", e);
            throw new IOException("Error connecting to service", e);
        } finally {

            if (conn2 != null) {
                conn2.disconnect();
            }
        }

        // Cria os marcadores dos onibus.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    markerBus(json2.toString());

                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Erro no processo", e);
                }
            }
        });
    }

    void createMarkersFromJson(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = jsonArray.getJSONObject(i);


            map.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pontoroxo))
                            .title("Escolha EMBARQUE ou DESEMBARQUE")
                            .snippet(jsonObj.getString("NOME"))
                            .position(
                                    new LatLng(
                                            Double.valueOf(jsonObj.getString("LAT").replace(",", ".")),
                                            Double.valueOf(jsonObj.getString("LON").replace(",", "."))

                                    )
                            )
            );


            //Configuramos nosso adaptador em nosso mapa
            map.setInfoWindowAdapter(this);

            //Repitimos o processo do passo anterior apenas para termos um marcador com informações a mostrar
            map.setOnMyLocationChangeListener(null);
        }
    }


    void markerBus(String json2) throws JSONException {
        JSONArray jsonArray = new JSONArray(json2);
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonBus = jsonArray.getJSONObject(i);

            map.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pontobus))
                            .title("Prefixo do ônibus " + "|" + " Cód Linha" + "|" + " Adaptado?")
                            .snippet("        " + jsonBus.getString("PREFIXO") + "   |      " + jsonBus.getString("LINHA") + "  |" + jsonBus.getString("ADAPT"))
                            .position(
                                    new LatLng(
                                            Double.valueOf(jsonBus.getString("LAT").replace(",", ".")),
                                            Double.valueOf(jsonBus.getString("LON").replace(",", "."))
                                    )
                            )

            );
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    @Override
    public View getInfoContents(Marker marker) {
        //Inflamos o nosso arquivo de layout em uma view
        markerInfo = getLayoutInflater().inflate(R.layout.activity_click_bus, null);

        //Configuramos o tamanho de nossa view
        markerInfo.setLayoutParams(new RelativeLayout.LayoutParams(600, 300));

        //Vinculamos objetos com os componentes de tela em nossa view
        TextView titulo = (TextView) markerInfo.findViewById(R.id.infowindow_textview_titulo);
        TextView descricao = (TextView) markerInfo.findViewById(R.id.infowindow_textview_descricao);

        //Recuperamos os valores de nosso marcador e o colocamos em nossa view
        titulo.setText(marker.getTitle());
        descricao.setText(marker.getSnippet());

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                //    alertMeioTransporte();

                Intent intent = new Intent(MapsActivity.this, EmbarqueDesembarque.class);
                startActivity(intent);

            }

        });
        //Retornamos para a tela a nossa view personalizada
        return markerInfo;
    }

    public void todaslinhas(final View view) {

        Button buttonLinhas = (Button) view.findViewById(R.id.buttonLinhas);
        buttonLinhas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, ConsumirJsonLinhas.class);
                startActivity(intent);
            }
        });
    }
    // metodo para chamar a lista salva no banco
    public void getActivity(View view) {
        Button bt = (Button) view;
        Intent intent;

        intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }

    public void socketEnviarDados(View view) {
        socketDadosTrajeto();
    }

    private void socketDadosTrajeto() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pesquisa Origem Destino");
        builder.setMessage("Deseja Contribuir com a Pesquisa Origem e Destino?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MapsActivity.this, "Obrigado pela colaboração ", Toast.LENGTH_SHORT).show();
                final String host = "10.10.86.141";
                final String port = "2016";

                // Instancia a classe de conexão com socket
                st = new SocketTask(host, Integer.parseInt(port), 5000) {
                    @Override
                    protected void onProgressUpdate(String... progress) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "dd/MM/yyyy HH:mm:ss");
                    }
                };

                String salvarSocket = origin.getText().toString()
                        + "," + edLatitude.getText().toString()
                        + "," + edLongitude.getText().toString()
                        + "," + destination.getText().toString()
                        + "," + edLatitudeDestino.getText().toString()
                        + "," + edLongitudeDestino.getText().toString();


                st.execute(salvarSocket);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MapsActivity.this, "Ok Obrigado" , Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }


}
