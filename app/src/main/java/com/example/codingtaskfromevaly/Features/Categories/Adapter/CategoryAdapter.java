package com.example.codingtaskfromevaly.Features.Categories.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> allproduct) {
        this.context = context;
        this.categories = allproduct;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false);

        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderProduct)holder).tvProductname.setText(categories.get(position).getName());
        Glide.with(context)
                .load(categories.get(position).getImage_url())
                .into(((ViewHolderProduct)holder).ivPicture);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class ViewHolderProduct extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPicture)
        ImageView ivPicture;
        @BindView(R.id.tvProductname)
        TextView tvProductname;


        ViewHolderProduct(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
