package com.example.codingtaskfromevaly.Repository.WebServices;

import com.example.codingtaskfromevaly.Features.Categories.CategoriModel;
import com.example.codingtaskfromevaly.Features.Products.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EvalyApi {
    @GET("categories/")
    Call<List<CategoriModel>> getcategoryList();

    @GET("inventory/products/")
    Call<ProductModel> getProducts(@Query("category") String category,
                                   @Query("page") int page,
                                   @Query("limit") int limit
    );
}
