package com.ronzhin.tips.webserver.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")

@NamedQueries({
        @NamedQuery(name = User.QUERY_FIND_BY_LOGIN, query = "select u from User u where login = :login"),
        @NamedQuery(name = User.QUERY_FIND_BY_ROLE, query = "select u from User u where role = :role")
})
public class User implements IDEntity<Long> {

    public static final String QUERY_FIND_BY_LOGIN = "find_user_by_login";
    public static final String QUERY_FIND_BY_ROLE = "find_user_by_role";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "login")
    @Getter
    @Setter
    private String login;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Phone> phones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @Getter
    @Setter
    private ROLE role;

    @Override
    public String toString() {
        return String.format("User[id: %d, name: %s, %s, phones: %s, %s]", id, name, address, phones, role);
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