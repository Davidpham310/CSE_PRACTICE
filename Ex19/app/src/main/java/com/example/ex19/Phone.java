package com.example.ex19;

public class Phone {
    private int imgPhone;
    private String txtphone;

    public Phone() {
    }

    public Phone(int imgPhone, String txtphone) {
        this.imgPhone = imgPhone;
        this.txtphone = txtphone;
    }

    public String getTxtphone() {
        return txtphone;
    }

    public void setTxtphone(String txtphone) {
        this.txtphone = txtphone;
    }

    public int getImgPhone() {
        return imgPhone;
    }

    public void setImgPhone(int imgPhone) {
        this.imgPhone = imgPhone;
    }
}
