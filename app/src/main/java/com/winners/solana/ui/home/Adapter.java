package com.winners.solana.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.winners.solana.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_GRPAH = 0;
    private static final int TYPE_TIME_BAR = 1;
    private static final int TYPE_ITEM = 2;

    ArrayList<CellItem> cellItems;
    ArrayList<CardItem> cardItems;

    public Adapter(ArrayList<CardItem> cardItems, ArrayList<CellItem> cellItems) {
        this.cellItems = cellItems;
        this.cardItems = cardItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_cell_info, parent, false);
            return new ViewHolderItem(view);
        } else if (viewType == TYPE_GRPAH) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_graph, parent, false);
            return new ViewHolderGraph(view);
        }else if (viewType == TYPE_TIME_BAR) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_time_bar, parent, false);
            return new ViewHolderTimeBar(view);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            //String dataItem = getItem(position);
            //cast holder to VHItem and set data
        } else if (holder instanceof ViewHolderGraph) {
            //cast holder to VHHeader and set data for header.
        } else if (holder instanceof ViewHolderTimeBar) {
            //cast holder to VHHeader and set data for header.
        }
    }

    @Override
    public int getItemCount() {
        return cardItems.size() + cellItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_GRPAH;
        else if (position == 1)
            return TYPE_TIME_BAR;

        return TYPE_ITEM;
    }

    class ViewHolderGraph extends RecyclerView.ViewHolder {
        public ViewHolderGraph(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderTimeBar extends RecyclerView.ViewHolder {
        public ViewHolderTimeBar(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
        }
    }


}
