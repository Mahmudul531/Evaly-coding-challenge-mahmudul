package com.example.codingtaskfromevaly.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDao;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDatabase;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

public class ProductRepository {
    EvalyDao evalyDao;
    LiveData<List<Products>> getallproduct;

    public ProductRepository(Application application) {
        EvalyDatabase database = EvalyDatabase.getInstance(application);
        evalyDao = database.evalyDao();
        getallproduct = evalyDao.getallproduct();
    }

    public LiveData<List<Products>> getGetallproduct() {
        return getallproduct;
    }
    public void insert(Products products) {
        new InsertProducts(evalyDao).execute(products);
    }

    public void insert(Category category) {
        new InsertCategory(evalyDao).execute(category);
    }


    private static class InsertCategory extends AsyncTask<Category, Void, Void> {
        private EvalyDao evalyDao;

        private InsertCategory(EvalyDao evalyDao) {
            this.evalyDao = evalyDao;
        }


        @Override
        protected Void doInBackground(Category... categories) {
            evalyDao.insert(categories[0]);
            return null;
        }
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



}
