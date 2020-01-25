package com.example.codingtaskfromevaly.Repository.RoomPersistance;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.List;

@Dao
public interface EvalyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Products products);

    @Query("DELETE FROM products where category = :category")
    void deleteAllProducts(String category);

    @Query("DELETE FROM category")
    void deleteAllCategory();

    @Query("SELECT * FROM category ORDER BY id ASC")
    LiveData<List<Category>> getallcategory();

    @Query("SELECT * FROM products where category = :category ORDER BY id ASC")
    LiveData<List<Products>> getallproduct(String category);


}
