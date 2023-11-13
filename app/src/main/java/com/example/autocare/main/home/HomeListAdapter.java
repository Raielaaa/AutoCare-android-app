package com.example.autocare.main.home;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autocare.R;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
//    private boolean added = false;
    private ArrayList<HomeListModel> collection;
    private final LayoutInflater inflater;
    private final ItemClickListener addClickListener;
    private final ItemClickListener deleteClickListener;

    public HomeListAdapter(
            ArrayList<HomeListModel> collection,
            Context context,
            ItemClickListener addClickListener,
            ItemClickListener deleteClickListener
    ) {
        this.collection = collection;
        this.inflater = LayoutInflater.from(context);
        this.addClickListener = addClickListener;
        this.deleteClickListener = deleteClickListener;
    }


    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_list_item, parent, false);
        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
        final HomeListModel collection = this.collection.get(position);

        holder.tvProduct.setText(collection.getProduct());
        holder.tvCode.setText(collection.getCode());
        holder.ivImage.setImageURI(collection.getImageUri());

        holder.cvAdd.setOnClickListener(view -> {
            if (addClickListener != null) {
                addClickListener.onItemClick(view, position);
            }
        });

        holder.cvDelete.setOnClickListener(view -> {
            if (deleteClickListener != null) {
                deleteClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return collection.size();
    }

    public void setCollection(ArrayList<HomeListModel> filteredList) {
        collection = filteredList;
        notifyDataSetChanged();
    }

    public ArrayList<HomeListModel> getCollection() {
        return collection;
    }

    static class HomeListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvProduct;
        TextView tvCode;
        CardView cvAdd;
        CardView cvDelete;
        TextView tvAdd;

        HomeListViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvCode = itemView.findViewById(R.id.tvCode);
            cvAdd = itemView.findViewById(R.id.cvAdd);
            cvDelete = itemView.findViewById(R.id.cvDelete);
            tvAdd = itemView.findViewById(R.id.tvAdd);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
