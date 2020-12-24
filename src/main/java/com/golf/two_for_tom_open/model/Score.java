package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private int strokes;
}
