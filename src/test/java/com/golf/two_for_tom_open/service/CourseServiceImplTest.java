package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Course;
import com.golf.two_for_tom_open.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CourseServiceImpl.class)
class CourseServiceImplTest {

    @TestConfiguration
    static class CourseServiceImplTestContextConfiguration {

        @Bean
        public CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    CourseService courseService;

    @MockBean
    CourseRepository courseRepository;

    private final Course courseA = Course.builder()
            .courseName("Course A")
            .holes(Collections.emptyList())
            .build();
    private final Course courseB = Course.builder()
            .courseName("Course B")
            .holes(Collections.emptyList())
            .build();
    
    @Test
    void getAll() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(courseA, courseB));
        List<Course> courses = courseService.getAll();
        assertThat(courses, contains(courseA, courseB));
        assertThat(courses, hasSize(2));
    }

    @Test
    void save() {
        when(courseRepository.save(courseA)).thenReturn(courseA);
        Course course = courseService.save(courseA);
        assertThat(course, is(courseA));
    }
}