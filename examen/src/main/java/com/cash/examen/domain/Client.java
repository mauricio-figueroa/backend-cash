package com.cash.examen.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "articles")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

}
