package com.example.codingtaskfromevaly.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingtaskfromevaly.Features.Categories.CategoriModel;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDao;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.EvalyDatabase;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.WebServices.EvalyApi;
import com.example.codingtaskfromevaly.Repository.WebServices.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    EvalyDao evalyDao;
    LiveData<List<Category>> getallcategory;
    static CategoryRepository categoryRepository;
    EvalyApi evalyAPI;

    public CategoryRepository(Application application) {
        EvalyDatabase database = EvalyDatabase.getInstance(application);
        evalyDao = database.evalyDao();
        getallcategory = evalyDao.getallcategory();
        evalyAPI = RetrofitService.cteateService(EvalyApi.class);

    }


    public MutableLiveData<List<CategoriModel>> getCategoryserver() {
        MutableLiveData<List<CategoriModel>> categoriModelMutableLiveData = new MutableLiveData<>();
        evalyAPI.getcategoryList().enqueue(new Callback<List<CategoriModel>>() {
            @Override
            public void onResponse(Call<List<CategoriModel>> call,
                                   Response<List<CategoriModel>> response) {
                if (response.isSuccessful()) {
                    categoriModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CategoriModel>> call, Throwable t) {
                categoriModelMutableLiveData.setValue(null);
            }
        });
        return categoriModelMutableLiveData;

    }


    public LiveData<List<Category>> getGetallcategory() {
        return getallcategory;
    }


    public void insert(Category category) {
        new InsertCategory(evalyDao).execute(category);
    }

    public void deleteCategory() {
       new DeleteAllCategory(evalyDao).execute();
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

    private static class DeleteAllCategory extends AsyncTask<Void, Void, Void> {
        private EvalyDao evalyDao;

        private DeleteAllCategory(EvalyDao evalyDao) {
            this.evalyDao = evalyDao;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            evalyDao.deleteAllCategory();

            return null;
        }
    }


}
