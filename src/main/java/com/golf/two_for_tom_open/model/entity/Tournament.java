package com.golf.two_for_tom_open.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tournament")
public class Tournament extends BaseEntity {

    @Column
    private Year _year;

    //unidirectional
    @ManyToMany
    @JoinTable(name = "tournament_courses",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Builder.Default
    private List<Course> courses = new ArrayList<>();

    //unidirectional
    @ManyToMany
    @JoinTable(name = "tournament_players",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    @Builder.Default
    private List<Player> players = new ArrayList<>();

}
