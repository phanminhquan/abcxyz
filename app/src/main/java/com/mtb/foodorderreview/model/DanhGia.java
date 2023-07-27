package com.mtb.foodorderreview.model;

public class DanhGia {
    private Integer id;
    private String noiDung;
    private Double giaTien;
    private Integer rate;
    private Integer idUser;
    private Integer idNhaHangFood;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdNhaHangFood() {
        return idNhaHangFood;
    }

    public void setIdNhaHangFood(Integer idNhaHangFood) {
        this.idNhaHangFood = idNhaHangFood;
    }

    public DanhGia(Integer id, String noiDung, Double giaTien, Integer rate, Integer idUser, Integer idNhaHangFood) {
        this.id = id;
        this.noiDung = noiDung;
        this.giaTien = giaTien;
        this.rate = rate;
        this.idUser = idUser;
        this.idNhaHangFood = idNhaHangFood;
    }
}
