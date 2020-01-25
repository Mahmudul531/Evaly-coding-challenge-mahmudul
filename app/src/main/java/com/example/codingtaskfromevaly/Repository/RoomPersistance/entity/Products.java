package com.example.codingtaskfromevaly.Repository.RoomPersistance.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products", indices = @Index(value = {"slag"}, unique = true))
public class Products {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String slag;
    public String product_image;
    public String price_type;
    public String max_price;
    public String min_price;
    public String min_discounted_price;
    public String category;

    public Products(String name, String slag, String product_image, String price_type, String max_price, String min_price, String min_discounted_price, String category) {
        this.name = name;
        this.slag = slag;
        this.product_image = product_image;
        this.price_type = price_type;
        this.max_price = max_price;
        this.min_price = min_price;
        this.min_discounted_price = min_discounted_price;
        this.category = category;
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

    public String getProduct_image() {
        return product_image;
    }

    public String getPrice_type() {
        return price_type;
    }

    public String getMax_price() {
        return max_price;
    }

    public String getMin_price() {
        return min_price;
    }

    public String getMin_discounted_price() {
        return min_discounted_price;
    }

    public String getCategory() {
        return category;
    }
}
