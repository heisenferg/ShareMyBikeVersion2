package com.example.sharemybike;

import static com.example.sharemybike.ui.gallery.GalleryFragment.mValues;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sharemybike.ui.gallery.GalleryFragment;
import com.example.sharemybike.ui.home.HomeFragment;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsFragment extends Fragment {
   // GoogleMap mMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            GoogleMap mMap = googleMap;
            Log.d("MAPA", " Carga aquí");

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            PolylineOptions poly = new PolylineOptions();
            //for each bike in the list
            for (Bike c : mValues) {

                //gets its latitude and longitude
                LatLng ll = new LatLng(Double.valueOf(c.getLatitude()), Double.valueOf(c.getLongitude()));
                //adds a marker on the map
                mMap.addMarker(new MarkerOptions().position(ll).title(c.getCity()).snippet(String.valueOf(c.getOwner()) + "%"));
                builder.include(ll);
                //adds also a point in the polyline
                poly.add(ll);
            }

            mMap.addPolyline(poly);
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
            mMap.animateCamera(cu);

        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }



}