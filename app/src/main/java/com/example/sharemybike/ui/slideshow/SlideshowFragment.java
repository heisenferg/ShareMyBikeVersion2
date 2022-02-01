package com.example.sharemybike.ui.slideshow;

import static com.example.sharemybike.ui.gallery.GalleryFragment.mValues;

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
    private @NonNull
    FragmentMapsBinding binding;

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

}

