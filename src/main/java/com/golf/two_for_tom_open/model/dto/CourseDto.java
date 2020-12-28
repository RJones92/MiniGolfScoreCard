package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Hole;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CourseDto {

    private int id;
    private String courseName;
    private List<Hole> holes;

}
