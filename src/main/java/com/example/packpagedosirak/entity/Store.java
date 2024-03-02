package com.example.packpagedosirak.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Store {

    @Id
    private Long storesid;

    @Column(unique = false) // 중복값 허용
    private String storesname;
    private String distance;
    private String foodcategory;
    private String storesaddress;
    private String storesphone;
    private String operationhour;
    private Long likescount;
    private Long categoryid;

    @Override
    public String toString() {
        return "Store{" +
                "storesid=" + storesid +
                ", storesname='" + storesname + '\'' +
                ", distance='" + distance + '\'' +
                ", foodcategory='" + foodcategory + '\'' +
                ", storesaddress='" + storesaddress + '\'' +
                ", storesphone='" + storesphone + '\'' +
                ", operationhour='" + operationhour + '\'' +
                ", likescount='" + likescount + '\'' +
                ", categoryid=" + categoryid  + '\'' +
                '}';
    }

    public Long getStoresid() {
        return storesid;
    }

    public void setStoresid(Long storesid) {
        this.storesid = storesid;
    }

    public String getStoresname() {
        return storesname;
    }

    public void setStoresname(String storesname) {
        this.storesname = storesname;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFoodcategory() {
        return foodcategory;
    }

    public void setFoodcategory(String foodcategory) {
        this.foodcategory = foodcategory;
    }

    public String getStoresaddress() {
        return storesaddress;
    }

    public void setStoresaddress(String storesaddress) {
        this.storesaddress = storesaddress;
    }

    public String getStoresphone() {
        return storesphone;
    }

    public void setStoresphone(String storesphone) {
        this.storesphone = storesphone;
    }

    public String getOperationhour() {
        return operationhour;
    }

    public void setOperationhour(String operationhour) {
        this.operationhour = operationhour;
    }

    public Long getLikescount() {
        return likescount;
    }

    public void setLikescount(Long likescount) {
        this.likescount = likescount;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }



    public Store(){
        this.storesid = null;

    }

    public Store(Long storesid, String storesname, String distance, String foodcategory, String storesaddress, String storesphone, String operationhour, Long likescount, Long categoryid) {
        this.storesid = storesid;
        this.storesname = storesname;
        this.distance = distance;
        this.foodcategory = foodcategory;
        this.storesaddress = storesaddress;
        this.storesphone = storesphone;
        this.operationhour = operationhour;
        this.likescount = likescount;
        this.categoryid = categoryid;

    }



}
