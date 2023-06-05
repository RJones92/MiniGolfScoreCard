package com.golf.two_for_tom_open.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
