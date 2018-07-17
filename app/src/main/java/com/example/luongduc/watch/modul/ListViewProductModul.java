package com.example.luongduc.watch.modul;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class ListViewProductModul {
    public String imgLvProduct;
    public String nameLvProduct;
    public String describleLvProduct;

    public ListViewProductModul(String imgLvProduct, String nameLvProduct, String describleLvProduct) {
        this.imgLvProduct = imgLvProduct;
        this.nameLvProduct = nameLvProduct;
        this.describleLvProduct = describleLvProduct;
    }

    public String getImgLvProduct() {
        return imgLvProduct;
    }

    public void setImgLvProduct(String imgLvProduct) {
        this.imgLvProduct = imgLvProduct;
    }

    public String getNameLvProduct() {
        return nameLvProduct;
    }

    public void setNameLvProduct(String nameLvProduct) {
        this.nameLvProduct = nameLvProduct;
    }

    public String getDescribleLvProduct() {
        return describleLvProduct;
    }

    public void setDescribleLvProduct(String describleLvProduct) {
        this.describleLvProduct = describleLvProduct;
    }
}
