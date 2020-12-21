package com.golf.two_for_tom_open.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "players")
@Data
public class Player extends BaseEntity {

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;
}
