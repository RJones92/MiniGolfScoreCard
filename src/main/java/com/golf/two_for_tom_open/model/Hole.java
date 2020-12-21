package com.golf.two_for_tom_open.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "holes")
@Data
public class Hole extends BaseEntity {

    @Column
    private byte par;

    @Column
    private byte holeNumber;
}
