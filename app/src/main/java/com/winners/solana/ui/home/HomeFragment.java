package com.winners.solana.ui.home;

import android.os.Bundle;
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

import com.winners.solana.R;
import com.winners.solana.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
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