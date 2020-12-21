package com.golf.two_for_tom_open.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course extends BaseEntity {

    @Column
    private String courseName;

    @Column
    @OneToMany
    private List<Hole> holes;
}
