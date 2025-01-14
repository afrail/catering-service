package com.catering.service.auth.model;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Data
public class UserRoleAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

}
