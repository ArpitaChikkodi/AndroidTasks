package com.example.maps;

  import android.Manifest;
  import android.content.pm.PackageManager;
  import android.location.Location;
  import android.location.LocationListener;
  import android.location.LocationManager;
  import android.os.Bundle;

  import androidx.core.app.ActivityCompat;
  import androidx.fragment.app.FragmentActivity;

   import com.google.android.gms.maps.CameraUpdateFactory;
   import com.google.android.gms.maps.GoogleMap;
   import com.google.android.gms.maps.OnMapReadyCallback;
   import com.google.android.gms.maps.SupportMapFragment;
   import com.google.android.gms.maps.model.LatLng;
   import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;

    private final long MINIMUM_TIME = 100;
    private final long MINIMUM_DIST = 5;

    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Device Location : " + latLng.toString()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME, MINIMUM_DIST, locationListener);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
}