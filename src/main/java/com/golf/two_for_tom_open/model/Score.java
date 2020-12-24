package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "scores")
public class Score extends BaseEntity {

    //bidirectional
    @ManyToOne
    private Player player;

    //bidirectional
    @ManyToOne
    private Tournament tournament;

    //bidirectional
    @ManyToOne
    private Hole hole;

    @Column
    private byte score;
}
