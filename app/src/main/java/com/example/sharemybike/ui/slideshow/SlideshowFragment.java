package com.example.sharemybike.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sharemybike.Bike;
import com.example.sharemybike.R;
import com.example.sharemybike.databinding.FragmentMapsBinding;
import com.example.sharemybike.databinding.FragmentSlideshowBinding;
import com.example.sharemybike.ui.gallery.GalleryFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class SlideshowFragment extends Fragment  {

    private SlideshowViewModel slideshowViewModel;
    private @NonNull FragmentMapsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // slideshowViewModel =
        //        new ViewModelProvider(this).get(SlideshowViewModel.class);
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
   /* @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap mMap=googleMap;
        Log.d("MAPA", " Carga aquí");

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions poly=new PolylineOptions();
        //for each bike in the list
        for (Bike c : mValues) {

            //gets its latitude and longitude
            LatLng ll = new LatLng(Double.valueOf(c.getLatitude()), Double.valueOf(c.getLongitude()));
            //adds a marker on the map
            mMap.addMarker(new MarkerOptions().position(ll).title(c.getCity()).snippet(String.valueOf(c.getOwner())+"%"));
            builder.include(ll);
            //adds also a point in the polyline
            poly.add(ll);
        }

        mMap.addPolyline(poly);
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
        mMap.animateCamera(cu);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));

    }
*/}