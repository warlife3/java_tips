package com.ronzhin.tips.webserver.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class ROLE implements IDEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    public ROLE(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("role: %s", name);
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