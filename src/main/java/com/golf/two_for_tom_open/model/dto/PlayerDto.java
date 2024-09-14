package com.golf.two_for_tom_open.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.golf.two_for_tom_open.model.dto.stat.Stat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PlayerDto {

    private int id;
    private String firstName;
    private String lastName;
    private List<Stat> playerStats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDto playerDto = (PlayerDto) o;

        if (id != playerDto.id) return false;
        return Objects.equals(firstName, playerDto.firstName) &&
                Objects.equals(lastName, playerDto.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
