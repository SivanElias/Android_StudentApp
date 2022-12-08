package com.example.studentapp.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String address;
    private String phone;
    private Boolean checked;

    public Student(String name, String id, String address, String phone, Boolean checked) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
