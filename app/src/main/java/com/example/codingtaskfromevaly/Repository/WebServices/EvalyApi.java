package com.example.codingtaskfromevaly.Repository.WebServices;

import com.example.codingtaskfromevaly.Features.Categories.CategoriModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EvalyApi {
    @GET("categories/")
    Call<List<CategoriModel>> getcategoryList();
}
