package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Hole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Getter
@Setter
@Component
public class CourseDto {

    private String courseName;
    private List<Hole> holes;

}
