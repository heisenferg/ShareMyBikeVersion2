package com.example.sharemybike.ui.gallery;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sharemybike.Bike;
import com.example.sharemybike.BikesContent;
import com.example.sharemybike.MyItemRecyclerViewAdapter;
import com.example.sharemybike.R;
import com.example.sharemybike.User;
import com.example.sharemybike.UserBooking;
import com.example.sharemybike.databinding.FragmentGalleryBinding;
import com.example.sharemybike.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private int mColumnCount = 1;
    private BikesContent bikes;
    private Bike bike;

    public GalleryFragment() {
    }
com.example.sharemybike.MyItemRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(mValues));
        }
        loadBikesList();

        return view;
    }

    DatabaseReference mDatabase;
    public static ArrayList<Bike> mValues = new ArrayList<Bike>();

    public void loadBikesList() {
        if(mValues.isEmpty()) {
            mDatabase = FirebaseDatabase.getInstance("https://sharemybike-aa026-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
            mDatabase.child("bikes_list").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        Bike bike = productSnapshot.getValue(Bike.class);
                        downloadPhoto(bike);

                       mValues.add(bike);

                    }
                    for (int i=0; i<mValues.size();i++) {
                        String m = mValues.get(i).getDescription();
                        Log.d("ERROR:", "lee aquí" + m);
                    }
                   // adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }


    private void downloadPhoto(Bike c) {
            // Le he metido una dirección diferente porque tenía problemas con la principal.
        StorageReference mStorageReference = FirebaseStorage.getInstance("gs://sharemybike-339310.appspot.com").getReferenceFromUrl(c.getImage());
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            final File localFile = File.createTempFile("PNG_" + timeStamp, ".png");
            Log.d(TAG, "Loaded " + "Lee hasta aquí" + localFile.getAbsolutePath());



            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    //Insert the downloaded image in its right position at the ArrayList

                    String url = "gs://" + taskSnapshot.getStorage().getBucket() + "/" + taskSnapshot.getStorage().getName();

                    Log.d(TAG, "Loaded " + url);
                    for (Bike c : mValues) {
                        if (c.getImage().equals(url)) {
                            c.setPhoto(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                            //  notifyDataSetChanged();
                            Log.d(TAG, "Loaded pic " + c.getImage() + ";" + url + localFile.getAbsolutePath());
                        }
                    }
                }

            });
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    HomeFragment home= new HomeFragment();
    FirebaseUser usuario;


    public void reservar(){

        DatabaseReference mDatabase= FirebaseDatabase.getInstance("https://sharemybike-aa026-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        DatabaseReference hopperRef = mDatabase.child("reserve_list");
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("fecha_reserva", "Reservada el "+ home.selectedDate);
        usuario = FirebaseAuth.getInstance().getCurrentUser();

        hopperUpdates.put("Usuario", usuario.getDisplayName());


        hopperRef.updateChildren(hopperUpdates);



        //    Map<String, Object> updates = new HashMap<>();
        // updates.put("bikes_list/0/numero_reservas/", ServerValue.increment(1));
        //  updates.put("bikes_list/0/fecha_reserva/", fecha);
        //  mDatabase.updateChildren(updates);
        //mDatabase.setValue(fecha);
    }


        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}