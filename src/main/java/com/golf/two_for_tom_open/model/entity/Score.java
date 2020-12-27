package com.golf.two_for_tom_open.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    //unidirectional
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    //unidirectional
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    //unidirectional
    @ManyToOne
    @JoinColumn(name = "hole_id")
    private Hole hole;

    @Column
    private int strokes;
}
