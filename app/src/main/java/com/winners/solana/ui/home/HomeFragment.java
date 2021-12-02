package com.winners.solana.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winners.solana.R;
import com.winners.solana.databinding.FragmentHomeBinding;
import com.winners.solana.ui.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private final String TAG = "HOME_FRAGMENT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView tv_time = binding.tvTime;
        final RecyclerView rv = binding.rv;
        //final LineChart chart = binding.chart;

        //rv.addItemDecoration(new MarginDecoration(this));
        //rv.setHasFixedSize(true);
       // rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 || position == 1 ? 2 : 1;
            }
        });
        rv.setLayoutManager(manager);

        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        rv.addItemDecoration(decoration);


//        final Handler handler = new Handler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Date currentTime = Calendar.getInstance().getTime();
//                            tv_time.setText(currentTime.toString());
//                        }
//                    });
//                }
//            }
//        }).start();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = (DatabaseReference) database.getReference("");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue((HashMap<String, Float>).class);
                //HashMap<String, Double> map = (HashMap<String, Double>) dataSnapshot.getValue();
            //    HashMap<String, Double> map = (HashMap<String, Double>) dataSnapshot.getValue();

//                Double uv = (double) Math.round(map.get("UV Index") * 100) / 100;
//                Double energy = (double) Math.round(map.get("Energy") * 100) / 100;
//                Double power = (double) Math.round(map.get("Power") * 100) / 100;
//                Double angle = (double) Math.round(map.get("Angle") * 100) / 100;

                HashMap<String, String> map = (HashMap<String, String>) dataSnapshot.getValue();

                Double uv = (double) Math.round(Double.parseDouble(map.get("UV Index")) * 100) / 100;
                String energy = map.get("Energy");
                Double power = (double) Math.round(Double.parseDouble(map.get("Power")) * 100) / 100;
                String angle = map.get("Angle");

//                HashMap<String, Long> map = (HashMap<String, Long>) dataSnapshot.getValue();
//
//                Long uv = map.get("UV Index");
//                Long energy = map.get("Energy");
//                Long power = map.get("Power");
//                Long angle = map.get("Angle");

                rv.setAdapter(setUpAdapter(energy, power, uv, angle));
                //tv_time.setText(val.toString() + " mW/cm^2");
                //Log.d(TAG, "Value is: " + val);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return root;
    }

    private Adapter setUpAdapter(String energy, Double power, Double uv, String angle) {
    //private Adapter setUpAdapter(Double energy, Double power, Double uv, Double angle) {
    //private Adapter setUpAdapter(Long energy, Long power, Long uv, Long angle) {
        ArrayList<CellItem> cellItems = new ArrayList<CellItem>();
        ArrayList<CardItem> cardItems = new ArrayList<CardItem>();

//        for(int i =0; i < 6; i++) {
//            cellItems.add(new CellItem("",""));
//        }

        String value;
        @ColorInt int bgColor;

        if(power < 100 && uv >= 0.1) {
            value = "Degraded";
            bgColor = getResources().getColor(R.color.solrRed);
        } else {
            value = "Optimal";
            bgColor = getResources().getColor(R.color.solrGreen);
        }

        CellItem problemItem = new CellItem("Operation", value, bgColor, getResources().getColor(R.color.white));
        CellItem weatherItem = new CellItem("Weather", (uv <= 0.2 ? "Cloudy" : "Sunny"), getResources().getColor(R.color.solrGray), getResources().getColor(R.color.solrTextColor));

        CellItem energyItem = new CellItem("Energy (mWh)",energy.toString(), getResources().getColor(R.color.solrGray), getResources().getColor(R.color.solrTextColor));
        CellItem powerItem = new CellItem("Power (mW)",power.toString(), getResources().getColor(R.color.solrGray), getResources().getColor(R.color.solrTextColor));

        if(uv < 0)
            uv = 0.0;
        
        CellItem uvItem = new CellItem("UV Index (mw/cm^2)",uv.toString(), getResources().getColor(R.color.solrGray), getResources().getColor(R.color.solrTextColor));
        CellItem angleItem = new CellItem("Angle (degrees)",angle.toString(), getResources().getColor(R.color.solrGray), getResources().getColor(R.color.solrTextColor));

        cellItems.add(problemItem);
        cellItems.add(weatherItem);
        cellItems.add(energyItem);
        cellItems.add(powerItem);
        cellItems.add(uvItem);
        cellItems.add(angleItem);

        for(int i =0; i < 0; i++) {
            cardItems.add(new CardItem());
        }

        return new Adapter(cardItems, cellItems, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}