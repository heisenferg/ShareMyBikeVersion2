package com.example.sharemybike.ui2.slideshow;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sharemybike.Bike;
import com.example.sharemybike.R;
import com.example.sharemybike.databinding.AddBikeFragmentBinding;
import com.example.sharemybike.databinding.FragmentFirstBinding;
import com.example.sharemybike.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment  {

    static final int PICK=100;
    Uri image;
AddBikeFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = AddBikeFragmentBinding.inflate(inflater, container, false);
        binding.buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        binding.buttonAddBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
nuevaBici();
            }
        });

        return binding.getRoot();




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(gallery, PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK) {
            image = data.getData();
            binding.ivPhoto.setImageURI(image);
        }
    }

    ArrayList<Bike> bici = new ArrayList<Bike>();

    public void nuevaBici(){
        String longitud = binding.etLongitud.getText().toString();
        String latitud = binding.etLatitud.getText().toString();
        String ciudad = binding.etCiudad.getText().toString();
        String direccion = binding.etDireccion.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();
        longitud = String.valueOf(Double.parseDouble(longitud));
        latitud = String.valueOf(Double.parseDouble(latitud));

        Bike bici = new Bike(ciudad, descripcion, Double.parseDouble(latitud), direccion,Double.parseDouble(longitud));
        GalleryFragment.mValues.add(bici);
    }
}