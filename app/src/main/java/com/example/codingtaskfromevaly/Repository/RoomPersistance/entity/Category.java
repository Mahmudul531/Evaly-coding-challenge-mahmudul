package com.example.codingtaskfromevaly.Repository.RoomPersistance.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import butterknife.Optional;

@Entity(tableName = "category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String slag;
    public String image_url;

    public Category(String name, String slag, String image_url) {
        this.name = name;
        this.slag = slag;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlag() {
        return slag;
    }

    public String getImage_url() {
        return image_url;
    }
}
