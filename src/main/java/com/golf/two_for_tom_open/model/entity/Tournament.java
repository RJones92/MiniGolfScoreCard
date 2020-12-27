package com.golf.two_for_tom_open.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tournament")
public class Tournament extends BaseEntity {

    @Column
    private Year year;

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
