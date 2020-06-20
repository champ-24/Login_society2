package com.example.login_society;

class ShopHelper {

    String shop_name,shop_owner_name,shop_contact,shop_address;

    public ShopHelper() {
    }

    public ShopHelper(String shop_name, String shop_owner_name, String shop_contact, String shop_address) {
        this.shop_name = shop_name;
        this.shop_owner_name = shop_owner_name;
        this.shop_contact = shop_contact;
        this.shop_address = shop_address;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public void setShop_owner_name(String shop_owner_name) {
        this.shop_owner_name = shop_owner_name;
    }

    public String getShop_contact() {
        return shop_contact;
    }

    public void setShop_contact(String shop_contact) {
        this.shop_contact = shop_contact;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
