package com.sip.camp_spring_day_4_5.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;

    @NotBlank(message="address is mandatory")
    @Column(name="address")
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Column(name="email")
    private String email;

    public Provider() {
    }

    public Provider(String name, String address, String email) {

        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
