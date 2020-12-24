package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @ManyToMany(mappedBy = "players")
    private Set<Tournament> tournaments;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Score> scores = new HashSet<>();

    public void addScore(Score score) {
        scores.add(score);
        score.setPlayer(this);
    }

    public void removeScore(Score score) {
        scores.remove(score);
        score.setPlayer(null);
    }

}
