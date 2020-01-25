package com.example.codingtaskfromevaly.Features.Products.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Products> allproduct;

    public ProductAdapter(Context context, List<Products> allproduct) {
        this.context = context;
        this.allproduct = allproduct;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products, parent, false);

        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderProduct)holder).tvProductname.setText(allproduct.get(position).getName());
        ((ViewHolderProduct)holder).tvPrice.setText(" Min: "+allproduct.get(position).getMin_price()+" Max: "+allproduct.get(position).getMax_price());
    }

    @Override
    public int getItemCount() {
        return allproduct.size();
    }


    class ViewHolderProduct extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPicture)
        ImageView ivPicture;
        @BindView(R.id.tvProductname)
        TextView tvProductname;
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        ViewHolderProduct(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
