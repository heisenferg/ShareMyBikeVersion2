package com.example.sharemybike.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharemybike.BikesContent;
import com.example.sharemybike.MapsFragment;
import com.example.sharemybike.MyItemRecyclerViewAdapter;
import com.example.sharemybike.R;
import com.example.sharemybike.databinding.FragmentFirstBinding;
import com.example.sharemybike.databinding.FragmentGalleryBinding;
import com.example.sharemybike.databinding.FragmentHomeBinding;
import com.example.sharemybike.ui.gallery.GalleryViewModel;

public class HomeFragment extends Fragment {

    private BikesContent bikes;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private HomeViewModel homeViewModel;
    private FragmentFirstBinding binding;
    public static String selectedDate="FECHA";
    int mes;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //  binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_first, container, false);

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // El mes me aparece como uno menos, así que le sumé 1 para que saliera correcto.
                mes = month + 1;
                binding.textViewFecha.setText(dayOfMonth + "/" + mes + "/" + year);
                selectedDate = dayOfMonth + "/" + mes + "/" + year;
            }

        });

        binding.buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}