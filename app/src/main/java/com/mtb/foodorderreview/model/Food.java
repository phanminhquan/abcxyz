package com.mtb.foodorderreview.model;

public class Food {
    private Integer id;
    private String ten;
    private Double giaTien;
    private String avatar;
    private Integer idLoai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Integer idLoai) {
        this.idLoai = idLoai;
    }
}
