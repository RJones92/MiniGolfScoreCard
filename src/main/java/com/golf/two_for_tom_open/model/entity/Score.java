package com.golf.two_for_tom_open.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
