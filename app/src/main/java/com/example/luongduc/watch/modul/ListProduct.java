package com.example.luongduc.watch.modul;

public class ListProduct {
    public int imgProductGridViewContent;
    public String nameProductGridViewContent;

    public ListProduct(int imgProductGridViewContent, String nameProductGridViewContent) {
        this.imgProductGridViewContent = imgProductGridViewContent;
        this.nameProductGridViewContent = nameProductGridViewContent;
    }

    public int getImgProductGridViewContent() {
        return imgProductGridViewContent;
    }

    public void setImgProductGridViewContent(int imgProductGridViewContent) {
        this.imgProductGridViewContent = imgProductGridViewContent;
    }

    public String getNameProductGridViewContent() {
        return nameProductGridViewContent;
    }

    public void setNameProductGridViewContent(String nameProductGridViewContent) {
        this.nameProductGridViewContent = nameProductGridViewContent;
    }
}
