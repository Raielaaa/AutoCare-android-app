package com.example.autocare.main.home;

import android.net.Uri;

public class HomeListModel {
    private Uri imageUri;
    private String product;
    private String code;

    public HomeListModel(Uri imageUri, String product, String code) {
        this.imageUri = imageUri;
        this.product = product;
        this.code = code;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}