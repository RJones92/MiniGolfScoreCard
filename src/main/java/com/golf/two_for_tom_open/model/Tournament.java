package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //bidirectional
    @ManyToMany
    @JoinTable(name = "tournament_players",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    @Builder.Default
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Score> scores = new HashSet<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.getTournaments().add(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.getTournaments().remove(this);
    }

    public void addScore(Score score) {
        scores.add(score);
        score.setTournament(this);
    }

    public void removeScore(Score score) {
        scores.remove(score);
        score.setTournament(null);
    }



}
