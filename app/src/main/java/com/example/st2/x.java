package com.example.st2;
public class x {

    private String img,name,type,origin,price,brief,qr;
    private int id;
    public x()
    {

    }
    public x(String img, String name, String type, String origin, String price, String brief,String qr,int id) {
        this.img = img;
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.price = price;
        this.brief = brief;
        this.qr=qr;
        this.id=id;
    }
    public String getQr(){return qr;}
    public void setQr(String qr){this.qr=qr;}
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    public int getId(){return this.id;}
    public void setId(int id){this.id=id;}
}
