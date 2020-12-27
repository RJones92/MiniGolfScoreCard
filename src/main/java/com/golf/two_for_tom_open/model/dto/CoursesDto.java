package com.golf.two_for_tom_open.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Builder
@Component
public class CoursesDto {

    private List<CourseDto> courses;

}
