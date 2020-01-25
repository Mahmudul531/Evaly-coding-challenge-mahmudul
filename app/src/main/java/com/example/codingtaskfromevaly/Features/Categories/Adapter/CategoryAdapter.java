package com.example.codingtaskfromevaly.Features.Categories.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.codingtaskfromevaly.Features.Categories.categoryClickListener;
import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Category> categories;
    private categoryClickListener<Category> categorycategoryClickListener;

    public CategoryAdapter(Context context, List<Category> allproduct, categoryClickListener<Category> categorycategoryClickListener) {
        this.context = context;
        this.categories = allproduct;
        this.categorycategoryClickListener = categorycategoryClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false);

        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderProduct) holder).tvProductname.setText(categories.get(position).getName());
        Glide.with(context)
                .load(categories.get(position).getImage_url())
                .into(((ViewHolderProduct) holder).ivPicture);
        ((ViewHolderProduct) holder).setCategorycategoryClickListener(categorycategoryClickListener);
        ((ViewHolderProduct) holder).setCategory(categories.get(position));
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
        private categoryClickListener<Category> categorycategoryClickListener;
        Category category;

        @OnClick(R.id.lin_category)
        public void onclickCategory() {
            categorycategoryClickListener.onclickOfCategory(true,category);
        }

        public void setCategorycategoryClickListener(categoryClickListener<Category> categorycategoryClickListener) {
            this.categorycategoryClickListener = categorycategoryClickListener;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        ViewHolderProduct(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
