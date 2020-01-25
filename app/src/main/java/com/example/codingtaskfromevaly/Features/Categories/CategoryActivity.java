package com.example.codingtaskfromevaly.Features.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskfromevaly.Features.Categories.Adapter.CategoryAdapter;
import com.example.codingtaskfromevaly.Features.Products.ProductActivity;
import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity implements categoryClickListener<Category> {
    CategoryViewModel categoryViewModel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllcategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                //  Toast.makeText(CategoryActivity.this, "Updated data product", Toast.LENGTH_SHORT).show();
                populateCategory(categories);
            }
        });

        categoryViewModel.getCategoriModelMutableLiveData().observe(this, categoriModel -> {
            categoryViewModel.deleteall();
            List<CategoriModel> mycategory = categoriModel;
            categoryViewModel.updateAllCategoryToLocal();
        });
    }

    public void populateCategory(List<Category> categories) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories, this);
        recyclerView.setAdapter(categoryAdapter);

    }


    @OnClick(R.id.fab)
    public void onViewClicked() {
        Category category = new Category("name", "slag", "image");
        categoryViewModel.insert(category);

    }

    @Override
    public void onclickOfCategory(boolean isClicked, Category categoryData) {
        if (isClicked) {
            ProductActivity.open(categoryData, this);
        }

    }
}
