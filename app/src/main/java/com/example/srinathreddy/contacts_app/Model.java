package com.example.srinathreddy.contacts_app;

/**
 * Created by srinathreddy on 01/11/17.
 */

public class Model {
    String Name;
    String Phone;
    String Email;
    String Address;
    int id;

    public Model() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Model(String Name, String Phone, String Email, String Address) {
        this.Email = Email;
        this.Name  =Name;
        this.Phone= Phone;
        this.Address= Address;
    }



}
