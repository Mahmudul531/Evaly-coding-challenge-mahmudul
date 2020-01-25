package com.example.codingtaskfromevaly.Repository.RoomPersistance;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

@Dao
public interface EvalyDao {

    @Insert
    void insert(Category category);

    @Insert
    void insert(Products products);

    @Query("DELETE FROM products")
    void deleteAllProducts();

    @Query("DELETE FROM category")
    void deleteAllCategory();

    @Query("SELECT * FROM category ORDER BY id ASC")
    LiveData<List<Category>> getallcategory();

    @Query("SELECT * FROM products ORDER BY id ASC")
    LiveData<List<Products>> getallproduct();


}
