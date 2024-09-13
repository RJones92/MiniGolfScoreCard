package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Course;
import com.golf.two_for_tom_open.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    CourseServiceImpl courseService;
    @Mock
    CourseRepository courseRepository;

    private final Course courseA = Course.builder()
            .courseName("Course A")
            .holes(Collections.emptyList())
            .build();

    @Test
    void save() {
        when(courseRepository.save(courseA)).thenReturn(courseA);
        Course course = courseService.save(courseA);
        assertThat(course, is(courseA));
    }
}