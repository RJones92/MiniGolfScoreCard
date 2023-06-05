package com.golf.two_for_tom_open.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "holes")
public class Hole extends BaseEntity {

    @Column
    private int par;

    @Column
    private int holeNumber;

}
