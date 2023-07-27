package com.mtb.foodorderreview.model;

public class DonHang {
    private Integer id;
    private Integer trangThai;
    private String ngayGio;
    private String ghiChu;
    private Integer idUser;

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    private Double tongTien;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(String ngayGio) {
        this.ngayGio = ngayGio;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public DonHang(Integer id, Integer trangThai, String ngayGio, String ghiChu, Integer idUser, Double tongTien) {
        this.id = id;
        this.trangThai = trangThai;
        this.ngayGio = ngayGio;
        this.ghiChu = ghiChu;
        this.idUser = idUser;
        this.tongTien = tongTien;
    }
}
