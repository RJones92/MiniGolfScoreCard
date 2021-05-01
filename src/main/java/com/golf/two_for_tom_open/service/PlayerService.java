package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.entity.Player;

import java.util.List;

public interface PlayerService extends BaseService<Player> {

    List<PlayerDto> getAllPlayerDtos();
}
