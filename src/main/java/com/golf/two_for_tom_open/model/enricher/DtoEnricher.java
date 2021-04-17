package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.TournamentDto;

public interface DtoEnricher {

    void enrich(TournamentDto tournament);
}
