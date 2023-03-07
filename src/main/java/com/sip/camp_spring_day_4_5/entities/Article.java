package com.sip.camp_spring_day_4_5.entities;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "label is Mandatory")
    @Column(name="label")
    private String label;

    @Column(name="price")
    private float price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="provider_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Article(String label, float price) {
        this.label = label;
        this.price = price;
    }

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public float getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", provider=" + provider.getName() +
                '}';
    }
}
