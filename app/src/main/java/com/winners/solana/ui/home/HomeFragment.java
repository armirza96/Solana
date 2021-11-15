package com.winners.solana.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.winners.solana.R;
import com.winners.solana.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        final TextView tv_time = binding.tvTime;
        final RecyclerView rv = binding.rv;

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
        rv.setAdapter(setUpAdapter());
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = (DatabaseReference) database.getReference("");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue((HashMap<String, Float>).class);
                HashMap<String, Double> map = (HashMap<String, Double>) dataSnapshot.getValue();
                Double val = map.get("UV Index");
                tv_time.setText(val.toString() + " mW/cm^2");
                Log.d(TAG, "Value is: " + val);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return root;
    }

    private Adapter setUpAdapter() {
        ArrayList<CellItem> cellItems = new ArrayList<CellItem>();
        ArrayList<CardItem> cardItems = new ArrayList<CardItem>();

        for(int i =0; i < 5; i++) {
            cellItems.add(new CellItem("",""));
        }

        for(int i =0; i < 3; i++) {
            cardItems.add(new CardItem());
        }

        return new Adapter(cardItems, cellItems);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}