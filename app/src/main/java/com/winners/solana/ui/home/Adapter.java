package com.winners.solana.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.winners.solana.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

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
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_graph, parent, false);
            return new ViewHolderHeader(view);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            //String dataItem = getItem(position);
            //cast holder to VHItem and set data
        } else if (holder instanceof ViewHolderHeader) {
            //cast holder to VHHeader and set data for header.
        }
    }

    @Override
    public int getItemCount() {
        return cardItems.size() + cellItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }



    class ViewHolderHeader extends RecyclerView.ViewHolder {
        public ViewHolderHeader(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
        }
    }


}
