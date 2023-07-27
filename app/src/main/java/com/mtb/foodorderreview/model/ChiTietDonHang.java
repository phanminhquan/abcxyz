package com.mtb.foodorderreview.model;

public class ChiTietDonHang {
    private Integer id;
    private Integer soLuong;
    private Integer idNhaHangFood;
    private Integer idDonHang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getIdNhaHangFood() {
        return idNhaHangFood;
    }

    public void setIdNhaHangFood(Integer idNhaHangFood) {
        this.idNhaHangFood = idNhaHangFood;
    }

    public Integer getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(Integer idDonHang) {
        this.idDonHang = idDonHang;
    }
}
