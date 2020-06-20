package com.example.login_society;

class ShopCardHelper {
    String shop_name,shop_contact;

    public ShopCardHelper() {
    }

    public ShopCardHelper(String shop_name, String shop_contact) {
        this.shop_name = shop_name;
        this.shop_contact = shop_contact;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_contact() {
        return shop_contact;
    }

    public void setShop_contact(String shop_contact) {
        this.shop_contact = shop_contact;
    }
}
