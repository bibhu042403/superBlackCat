package com.pareeksha.blackcat.hunter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GenericGenerator(name ="generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    int id;
    @Column(name = "first_name")
    String fName;
    @Column(name = "last_name")
    String lName;
    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "enabled")
    Integer enable = 0;
    @Column(name = "user_role")
    String userRole;
}
