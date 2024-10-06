package com.example.ex23;

public class List_item {
    private String img , title, info ;

    public List_item(String img, String title, String info) {
        this.img = img;
        this.title = title;
        this.info = info;

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
