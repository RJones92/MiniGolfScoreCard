package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "hole")
    @Builder.Default
    private Set<Score> scores = new HashSet<>();

    public void addScore(Score score) {
        scores.add(score);
        score.setHole(this);
    }

    public void removeScore(Score score) {
        scores.remove(score);
        score.setHole(null);
    }
}
