package com.mtb.foodorderreview.model;

public class User {
    private Integer id;
    private String ten;
    private String taiKhoan;
    private String matKhau;
    private String diaChi;
    private String soDienThoai;
    private Integer quyen;
    private String email;
    private String avatar;

    public User(Integer id, String ten, String taiKhoan, String matKhau, String diaChi, String soDienThoai, Integer quyen, String email, String avatar) {
        this.id = id;
        this.ten = ten;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.quyen = quyen;
        this.email = email;
        this.avatar = avatar;
    }

    public User(Integer id, String ten, String taiKhoan, String diaChi, String soDienThoai, String email) {
        this.id = id;
        this.ten = ten;
        this.taiKhoan = taiKhoan;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

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

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Integer getQuyen() {
        return quyen;
    }

    public void setQuyen(Integer quyen) {
        this.quyen = quyen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
