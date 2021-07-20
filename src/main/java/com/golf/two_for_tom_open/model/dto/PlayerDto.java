package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PlayerDto {

    private int id;
    private String firstName;
    private String lastName;
    private long countOfTournamentsPlayed;
    private long countOfTournamentsWon;
    private long countOfCoursesPlayed;
    private long countOfCoursesWon;
    private long countOfHolesPlayed;
    private long countOfHolesWon;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDto playerDto = (PlayerDto) o;

        if (id != playerDto.id) return false;
        if (firstName != null ? !firstName.equals(playerDto.firstName) : playerDto.firstName != null) return false;
        return lastName != null ? lastName.equals(playerDto.lastName) : playerDto.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
