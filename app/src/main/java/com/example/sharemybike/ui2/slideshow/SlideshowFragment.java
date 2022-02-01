package com.example.sharemybike.ui2.slideshow;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.sharemybike.Bike;
import com.example.sharemybike.MainActivity;
import com.example.sharemybike.databinding.AddBikeFragmentBinding;
import com.example.sharemybike.ui.gallery.GalleryFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SlideshowFragment extends Fragment {

    static final int PICK = 100;
    Uri image;
    AddBikeFragmentBinding binding;
    Bitmap foto;
    String longitud;
    String latitud;

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
                binding.ivPhoto.buildDrawingCache();
                nuevaBici();
            }
        });

        longitud = String.valueOf(MainActivity.longitud);
        binding.etLongitud.setText(longitud);
        latitud = String.valueOf(MainActivity.latitud);
        binding.etLatitud.setText(latitud);

        return binding.getRoot();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Abrir galería para seleccionar imagen
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case PICK:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();
                    if (requestCode == PICK) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getActivity().getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            foto = bmp;
                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                            binding.ivPhoto.setImageBitmap(bmp);

                        }
                    }
                }
                break;
        }
    }


    public void nuevaBici() {


        String ciudad = binding.etCiudad.getText().toString();
        String direccion = binding.etDireccion.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();
        longitud = String.valueOf(Double.parseDouble(longitud));
        latitud = String.valueOf(Double.parseDouble(latitud));
        String nombre = binding.etNombre.getText().toString();


        Bike bici = new Bike(ciudad, nombre, descripcion, Double.parseDouble(latitud), direccion, Double.parseDouble(longitud), foto);
        GalleryFragment.mValues.add(bici);
        Toast.makeText(getContext(), "Bici guardada correctamente", Toast.LENGTH_SHORT).show();
    }


    public void updateIU(){
        binding.etLongitud.setText(""+ MainActivity.longitud);
        binding.etLatitud.setText(""+MainActivity.latitud);
    }

}