package com.example.codingtaskfromevaly.Features.Products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModel {

    @Expose
    @SerializedName("results")
    private List<Results> results;
    @Expose
    @SerializedName("next")
    private String next;
    @Expose
    @SerializedName("count")
    private int count;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class Results {
        @Expose
        @SerializedName("min_discounted_price")
        private String min_discounted_price;
        @Expose
        @SerializedName("min_price")
        private String min_price;
        @Expose
        @SerializedName("max_price")
        private String max_price;
        @Expose
        @SerializedName("price_type")
        private String price_type;
        @Expose
        @SerializedName("product_image")
        private String product_image;
        @Expose
        @SerializedName("category")
        private String slug;
        @Expose
        @SerializedName("name")
        private String name;

        public String getMin_discounted_price() {
            return min_discounted_price;
        }

        public void setMin_discounted_price(String min_discounted_price) {
            this.min_discounted_price = min_discounted_price;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getMax_price() {
            return max_price;
        }

        public void setMax_price(String max_price) {
            this.max_price = max_price;
        }

        public String getPrice_type() {
            return price_type;
        }

        public void setPrice_type(String price_type) {
            this.price_type = price_type;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
