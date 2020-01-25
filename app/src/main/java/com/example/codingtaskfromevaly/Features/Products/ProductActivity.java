package com.example.codingtaskfromevaly.Features.Products;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskfromevaly.Features.Products.Adapter.ProductAdapter;
import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {
    ProductViewModel productViewModel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    boolean isFirstTime = true;
    boolean isFirstTimeProductLoad = true;
    int limit = 10;
    int page = 1;
    String category;
    List<Products> products;


    public static void open(Category category, Activity activity) {
        Intent intent = new Intent(activity, ProductActivity.class);
        intent.putExtra("category", new Gson().toJson(category));
        activity.startActivity(intent);

    }

    public void setData() {
        if (getIntent() != null) {
            Category category = new Gson().fromJson(getIntent().getStringExtra("category"), Category.class);
            this.category = category.getSlag();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouct_list);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setData();
        productViewModel =
                ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getAllproduct(category).observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                // Toast.makeText(ProductActivity.this, "Updated data product", Toast.LENGTH_SHORT).show();
                populateProduct(products);
            }
        });
        productViewModel.getProductModelMutableLiveData(category, page, limit).observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {
                if (productModel != null) {
                    productViewModel.updateAllCategoryToLocal(isFirstTime, category);
                    prepareNextRequestData(productModel);
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    // Toast.makeText(ProductActivity.this, "Last", Toast.LENGTH_LONG).show();
                    productViewModel.getProductModelMutableLiveData(category, page, limit).observe(ProductActivity.this, new Observer<ProductModel>() {
                        @Override
                        public void onChanged(ProductModel productModel) {
                            if (productModel != null) {
                                productViewModel.updateAllCategoryToLocal(isFirstTime, category);
                                prepareNextRequestData(productModel);

                            }
                        }
                    });
                }
            }
        });
    }

    public void prepareNextRequestData(ProductModel productModel) {
        String pagelocal = null;

        if (productModel.getNext() != null) {
            Uri uri = Uri.parse(productModel.getNext());
            try {
                pagelocal = URLDecoder.decode(uri.getQueryParameter("page"), "UTF-8");
                category = URLDecoder.decode(uri.getQueryParameter("category"), "UTF-8");
                page = Integer.parseInt(pagelocal);
            } catch (UnsupportedEncodingException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void populateProduct(List<Products> products) {
        ProductAdapter  productAdapter = new ProductAdapter(this, products);

        recyclerView.setAdapter(productAdapter);

    }


//    @OnClick(R.id.tx_inset_data)
//    public void onViewClicked() {
//        Products products = new Products("a", "b", "b", "b", "b", "b", "d", category);
//        productViewModel.insert(products);
//
//    }
}
