package com.example.codingtaskfromevaly.Features.Products;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.codingtaskfromevaly.Repository.ProductRepository;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    ProductRepository productRepository;
    LiveData<List<Products>> allproduct;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allproduct = productRepository.getGetallproduct();
    }

    public void insert(Products products) {
        productRepository.insert(products);
    }

    public LiveData<List<Products>> getAllproduct() {
        return allproduct;
    }
}
