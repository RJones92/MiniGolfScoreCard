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
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity@Table(name = "courses")
public class Course extends BaseEntity {

    @Column
    private String courseName;

    @Column
    @OneToMany
    private List<Hole> holes;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST)
    private Set<Tournament> tournaments;
}
