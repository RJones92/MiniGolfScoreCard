package com.golf.two_for_tom_open.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column
    private String courseName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @Builder.Default
    private List<Hole> holes = new ArrayList<>();

    public Optional<Hole> getHoleByCourseHoleNumber(int holeNumber) {
        return holes.stream()
                .filter(hole -> hole.getHoleNumber() == holeNumber)
                .findAny();
    }

}
