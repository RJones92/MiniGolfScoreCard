package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "holes")
public class Hole extends BaseEntity {

    @Column
    private byte par;

    @Column
    private byte holeNumber;
}
