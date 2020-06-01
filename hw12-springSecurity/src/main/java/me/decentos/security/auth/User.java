package me.decentos.security.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_acc_non_exp")
    private boolean isAccountNonExpired;

    @Column(name = "is_acc_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_cred_non_exp")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

}
