package com.ronzhin.tips.webserver.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor

@Entity
@Table(name = "phones")
public class Phone implements IDEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "number")
    @Getter
    @Setter
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    public Phone(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("number: %s", number);
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