package com.golf.two_for_tom_open.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity@Table(name = "courses")
public class Course extends BaseEntity {

    @Column
    private String courseName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @Builder.Default
    private List<Hole> holes = new ArrayList<>();
    
}
