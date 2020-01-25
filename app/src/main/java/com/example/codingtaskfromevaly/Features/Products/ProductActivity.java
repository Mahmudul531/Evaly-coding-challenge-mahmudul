package com.example.codingtaskfromevaly.Features.Products;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskfromevaly.Features.Products.Adapter.ProductAdapter;
import com.example.codingtaskfromevaly.R;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {
    ProductViewModel productViewModel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tx_inset_data)
    TextView txInsetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prouct_list);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel =
                ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getAllproduct().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                Toast.makeText(ProductActivity.this, "Updated data product", Toast.LENGTH_SHORT).show();
                populateProduct(products);
            }
        });
    }

    public void populateProduct(List<Products> products) {
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);

    }


    @OnClick(R.id.tx_inset_data)
    public void onViewClicked() {
        Products products = new Products("a","b","b","b","b","b","d");
        productViewModel.insert(products);

    }
}