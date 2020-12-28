package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Course;
import com.golf.two_for_tom_open.model.entity.Player;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TournamentDto {

    private int id;
    private Year year;
    private List<Course> courses;
    private List<Player> players;
}
