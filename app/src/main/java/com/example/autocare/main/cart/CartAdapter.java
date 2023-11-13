package com.example.autocare.main.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autocare.R;
import com.example.autocare.main.home.HomeListModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    //    private boolean added = false;
    private final ArrayList<HomeListModel> collection;
    private final LayoutInflater inflater;
    private final ItemClickListener deleteClickListener;

    public CartAdapter(
            ArrayList<HomeListModel> collection,
            Context context,
            ItemClickListener deleteClickListener
    ) {
        this.collection = collection;
        this.inflater = LayoutInflater.from(context);
        this.deleteClickListener = deleteClickListener;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_list_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final HomeListModel collection = this.collection.get(position);

        holder.tvProduct.setText(collection.getProduct());
        holder.tvCode.setText(collection.getCode());
        holder.ivImage.setImageURI(collection.getImageUri());

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

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvProduct;
        TextView tvCode;
        CardView cvDelete;

        CartViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImageCart);
            tvProduct = itemView.findViewById(R.id.tvProductCart);
            tvCode = itemView.findViewById(R.id.tvCodeCart);
            cvDelete = itemView.findViewById(R.id.cvDeleteCart);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
