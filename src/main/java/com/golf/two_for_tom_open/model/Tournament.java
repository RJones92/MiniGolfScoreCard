package com.golf.two_for_tom_open.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "tournament")
@Data
public class Tournament extends BaseEntity {

    @Column
    private Year year;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tournament_courses",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tournament_players",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;

}
