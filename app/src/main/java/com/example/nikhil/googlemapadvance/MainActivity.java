package com.example.nikhil.googlemapadvance;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    Button b1,b2,b3,b4,b5,b6;
    MapFragment mf;
    GoogleMap m_map;

    MarkerOptions vesu;    //21.138262, 72.778233
    MarkerOptions myclg;    //21.137947, 72.793372
    MarkerOptions itabashischool;  //35.741601, 139.689360
    MarkerOptions donquijote;   //35.741079, 139.707985

    //polyline
    LatLng vesu1=new LatLng(21.138262, 72.778233);
    LatLng myclg1=new LatLng(21.137947, 72.793372);
    LatLng itabashischool1=new LatLng(35.741601, 139.689360);
    LatLng donquijote1=new LatLng(35.741079, 139.707985);



    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();


    static final CameraPosition TOKYO = CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.butmap);
        b2=(Button)findViewById(R.id.butsat);
        b3=(Button)findViewById(R.id.buthyb);

        b4=(Button)findViewById(R.id.butmap1);
        b5=(Button)findViewById(R.id.butsat1);
        b6=(Button)findViewById(R.id.buthyb1);

        //types of map
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            }
        });

        //Map movement
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flyTo(SEATTLE);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flyTo(DUBLIN);

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flyTo(TOKYO);

            }
        });

        vesu=new MarkerOptions().position(new LatLng(21.138262, 72.778233)).title("Vesu");
        myclg=new MarkerOptions().position(new LatLng(21.137947, 72.793372)).title("BMCET");
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.clg));
        itabashischool=new MarkerOptions().position(new LatLng(35.741601, 139.689360)).title("Tokyo Itabashi");
        donquijote=new MarkerOptions().position(new LatLng(35.741079, 139.707985)).title("Don Quijote");

        mf=(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mf.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap map)
    {
        m_map=map;
        LatLng surat=new LatLng(21.1702,72.8311);
        CameraPosition target=CameraPosition.builder().target(surat).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        //marking
        m_map.addMarker(myclg);
        m_map.addMarker(donquijote);
        m_map.addMarker(vesu);
        m_map.addMarker(itabashischool);
        //polyline
        map.addPolyline(new PolylineOptions().geodesic(true)
                .add(vesu1).add(myclg1));
        //circle
        map.addCircle(new CircleOptions().center(itabashischool1).radius(5000)
                        .strokeColor(Color.GREEN)
                        .fillColor(Color.argb(64,0,255,0)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void flyTo(CameraPosition target)
    {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);

    }
}
