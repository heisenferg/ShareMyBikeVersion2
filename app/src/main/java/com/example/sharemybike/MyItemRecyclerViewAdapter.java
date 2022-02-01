package com.example.sharemybike;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharemybike.PlaceholderContent.PlaceholderItem;
import com.example.sharemybike.databinding.FragmentItemBinding;
import com.example.sharemybike.ui.gallery.GalleryFragment;
import com.example.sharemybike.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>  {

GalleryFragment gal = new GalleryFragment();


    public MyItemRecyclerViewAdapter(List<Bike> items) {
        gal.mValues = (ArrayList<Bike>) items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        holder.City.setText(gal.mValues.get(position).getCity());
        holder.Owner.setText(gal.mValues.get(position).getOwner());
        holder.photo.setImageBitmap(gal.mValues.get(position).getPhoto());
        holder.Location.setText(gal.mValues.get(position).getLocation());
        holder.Description.setText(gal.mValues.get(position).getDescription());
        holder.botonEmail.setOnClickListener(new View.OnClickListener () {
            @SuppressLint("IntentReset")
            @Override
            public void onClick (View v) {

                // Guardar en
                reservaBici(position);
            }
        });
    }

    FirebaseUser usuario;
    HomeFragment home = new HomeFragment();

    public void reservaBici(int position){
        DatabaseReference mDatabase= FirebaseDatabase.getInstance("https://sharemybike-aa026-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference hopperRef = mDatabase.child("reserve_list");

        UserBooking usuarios;
        usuarios = new UserBooking(usuario.getProviderId(), usuario.getEmail(), gal.mValues.get(position).getEmail(), gal.mValues.get(position).getCity(), home.selectedDate);

        Map<String, UserBooking> user = new HashMap<>();
        user.put(gal.mValues.get(position).getOwner(), usuarios);

        hopperRef.child(gal.mValues.get(position).getOwner()).setValue(user);

    }

    @Override
    public int getItemCount() {
        return gal.mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView City;
        public final TextView Owner;
        public final TextView Location;
        public final TextView Description;
        public final ImageView photo;
        public final ImageButton botonEmail;


        public BikesContent.Bike mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            City = binding.txtCity;
            Owner = binding.txtOwner;
            Location = binding.txtLocation;
            Description = binding.txtDescription;
            photo = binding.imgPhoto;
            botonEmail= binding.btnMail;

        }


    }
}