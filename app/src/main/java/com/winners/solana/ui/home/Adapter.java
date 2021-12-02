package com.winners.solana.ui.home;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.winners.solana.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_GRPAH = 0;
    private static final int TYPE_TIME_BAR = 1;
    private static final int TYPE_ITEM = 2;

    ArrayList<CellItem> cellItems;
    ArrayList<CardItem> cardItems;

    Context context;

    public Adapter(ArrayList<CardItem> cardItems, ArrayList<CellItem> cellItems, Context context) {
        this.cellItems = cellItems;
        this.cardItems = cardItems;
        this.context = context;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            CellItem item = cellItems.get(position);
            ((ViewHolderItem) holder).header.setText(item.getHeader());
            ((ViewHolderItem) holder).value.setText(item.getValue());

            ((ViewHolderItem) holder).header.setTextColor(item.getTextColor());
            ((ViewHolderItem) holder).value.setTextColor(item.getTextColor());

            ((ViewHolderItem) holder).background.setBackgroundColor(item.getBgColor());

        } else if (holder instanceof ViewHolderGraph) {
            //YourData[] dataObjects = ...;
            LineChart chart = ((ViewHolderGraph) holder).chart;
            List<Entry> entries = new ArrayList<Entry>();
//            for (YourData data : dataObjects) {
//                // turn your data into Entry objects
//                entries.add(new Entry(data.getValueX(), data.getValueY()));
//            }
            for(int i =0; i < 5; i++) {
                entries.add(new Entry(i, i*2));
            }

            LineDataSet dataSet = new LineDataSet(entries, "i*2");
            //dataSet.setLabel("Time");// add entries to dataset
            dataSet.setColor(R.color.purple_200);
            dataSet.setValueTextColor(R.color.white);
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate();
        } else if (holder instanceof ViewHolderTimeBar) {
            ProgressBar pb = ((ViewHolderTimeBar) holder).pb;

            pb.setMin(-9);
            pb.setMax(6);

            pb.setProgress(4);
        }
    }

    @Override
    public int getItemCount() {
        return cardItems.size() + cellItems.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_GRPAH;
//        else if (position == 1)
//            return TYPE_TIME_BAR;

        return TYPE_ITEM;
    }

    class ViewHolderGraph extends RecyclerView.ViewHolder {
        public final LineChart chart;
        public ViewHolderGraph(View itemView) {
            super(itemView);
            chart = itemView.findViewById(R.id.chart);
        }
    }

    class ViewHolderTimeBar extends RecyclerView.ViewHolder {
        public final ProgressBar pb;
        public ViewHolderTimeBar(View itemView) {
            super(itemView);
            pb = itemView.findViewById(R.id.pb);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public final TextView header;
        public final TextView value;
        public final ConstraintLayout background;
        public ViewHolderItem(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.tv_header);
            value = itemView.findViewById(R.id.tv_value);
            background = itemView.findViewById(R.id.background);
        }
    }


}
