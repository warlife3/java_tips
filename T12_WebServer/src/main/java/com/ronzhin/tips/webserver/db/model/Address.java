package com.ronzhin.tips.webserver.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor

@Entity
@Table(name = "addresses")
public class Address implements IDEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "street")
    @Getter
    @Setter
    private String street;

    public Address(String address) {
        this.street = address;
    }

    @Override
    public String toString() {
        return String.format("address: %s", street);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}