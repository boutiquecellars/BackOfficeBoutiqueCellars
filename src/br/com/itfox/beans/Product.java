/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

import java.sql.Blob;

/**
 *
 * @author belchiorpalma
 */
public class Product {
    private int productId;
    private String name;
    private float price;
    private Blob description;
    private String metaTagTitle;
    private String metaTagDescription;
    private String metaTagKeywords;
    private Blob pic1;
    private Blob pic2;
    private Blob pic3;
    private Blob pic4;
    private Blob pic5;
    private int brand;
    private String varietal;
    private String alcohol;
    private String region;
    private String year;
    private String mevushal;
    private int categoryId;
    
    public Product(){}

    public Product(int productId, String name, float price, Blob description, String metaTagTitle, String metaTagDescription, String metaTagKeywords, Blob pic1, Blob pic2, Blob pic3, Blob pic4, Blob pic5) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.metaTagTitle = metaTagTitle;
        this.metaTagDescription = metaTagDescription;
        this.metaTagKeywords = metaTagKeywords;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.pic5 = pic5;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        if(name==null){
            name="";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Blob getDescription() {
        return description;
    }

    public void setDescription(Blob description) {
        this.description = description;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public Blob getPic1() {
        return pic1;
    }

    public void setPic1(Blob pic1) {
        this.pic1 = pic1;
    }

    public Blob getPic2() {
        return pic2;
    }

    public void setPic2(Blob pic2) {
        this.pic2 = pic2;
    }

    public Blob getPic3() {
        return pic3;
    }

    public void setPic3(Blob pic3) {
        this.pic3 = pic3;
    }

    public Blob getPic4() {
        return pic4;
    }

    public void setPic4(Blob pic4) {
        this.pic4 = pic4;
    }

    public Blob getPic5() {
        return pic5;
    }

    public void setPic5(Blob pic5) {
        this.pic5 = pic5;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMevushal() {
        return mevushal;
    }

    public void setMevushal(String mevushal) {
        this.mevushal = mevushal;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    
    
    
}
