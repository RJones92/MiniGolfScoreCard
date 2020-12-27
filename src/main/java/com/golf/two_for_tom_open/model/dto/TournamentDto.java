package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Course;
import com.golf.two_for_tom_open.model.entity.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Builder
@Getter
@Setter
@Component
public class TournamentDto {

    private Year year;
    private List<Course> courses;
    private List<Player> players;
}
