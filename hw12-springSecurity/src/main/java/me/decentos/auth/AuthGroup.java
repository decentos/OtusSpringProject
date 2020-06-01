package me.decentos.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth_user_group")
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_user_group_id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "auth_group")
    private String authGroup;

}
