package com.example.codingtaskfromevaly.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingtaskfromevaly.Features.Categories.CategoriModel;
import com.example.codingtaskfromevaly.Features.Products.ProductModel;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDao;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDatabase;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;
import com.example.codingtaskfromevaly.Repository.WebServices.EvalyApi;
import com.example.codingtaskfromevaly.Repository.WebServices.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    EvalyDao evalyDao;
    LiveData<List<Products>> getallproduct;
    EvalyApi evalyAPI;


    public ProductRepository(Application application) {
        EvalyDatabase database = EvalyDatabase.getInstance(application);
        evalyDao = database.evalyDao();
        evalyAPI = RetrofitService.cteateService(EvalyApi.class);

    }

    public MutableLiveData<ProductModel> getProductFromServer(String category, int page, int limit) {
        MutableLiveData<ProductModel> productModelMutableLiveData = new MutableLiveData<>();
        evalyAPI.getProducts(category, page, limit).enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call,
                                   Response<ProductModel> response) {
                if (response.isSuccessful()) {
                    productModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                productModelMutableLiveData.setValue(null);
            }
        });
        return productModelMutableLiveData;

    }

    public LiveData<List<Products>> getGetallproduct(String category) {
        getallproduct = evalyDao.getallproduct(category);

        return getallproduct;
    }

    public void insert(Products products) {
        new InsertProducts(evalyDao).execute(products);
    }

    public void deleteproducts(String category) {
        new DeleteProduct(evalyDao).execute(category);
    }


    private static class InsertProducts extends AsyncTask<Products, Void, Void> {
        private EvalyDao evalyDao;

        private InsertProducts(EvalyDao evalyDao) {
            this.evalyDao = evalyDao;
        }


        @Override
        protected Void doInBackground(Products... products) {
            evalyDao.insert(products[0]);
            return null;
        }
    }

    private static class DeleteProduct extends AsyncTask<String, Void, Void> {
        private EvalyDao evalyDao;

        private DeleteProduct(EvalyDao evalyDao) {
            this.evalyDao = evalyDao;
        }


        @Override
        protected Void doInBackground(String... data) {
            evalyDao.deleteAllProducts(data[0]);
            return null;
        }
    }


}
