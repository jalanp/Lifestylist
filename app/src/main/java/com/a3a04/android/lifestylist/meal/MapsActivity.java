package com.a3a04.android.lifestylist.meal;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.a3a04.android.lifestylist.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String mActivityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mActivityType = getIntent().getExtras().getString("activity");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng mcmaster = new LatLng(43.261316, -79.919267);

        if (mActivityType.contains("meal")){
            LatLng bostonpizza = new LatLng(43.257226, -79.927708);
            LatLng timhortons = new LatLng(43.257742, -79.92572);
            LatLng subway = new LatLng(43.257507, -79.918782);
            LatLng eastmeetswest = new LatLng(43.262515, -79.922923);
            LatLng basilique = new LatLng(43.260939, -79.907413);
            mMap.addMarker(new MarkerOptions().position(bostonpizza).title("Boston Pizza"));
            mMap.addMarker(new MarkerOptions().position(timhortons).title("Tim Hortons"));
            mMap.addMarker(new MarkerOptions().position(subway).title("Subway"));
            mMap.addMarker(new MarkerOptions().position(eastmeetswest).title("East Meets West Bistro"));
            mMap.addMarker(new MarkerOptions().position(basilique).title("Basilique"));
        } else if (mActivityType.contains("workout")){
            LatLng dbac = new LatLng(43.262744, -79.917783);
            mMap.addMarker(new MarkerOptions().position(dbac).title("David Braley Athletic Centre"));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcmaster,15));
    }
}
