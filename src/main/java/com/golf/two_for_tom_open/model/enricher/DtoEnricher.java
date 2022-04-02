package com.golf.two_for_tom_open.model.enricher;

public interface DtoEnricher<T> {

    void enrich(T objectToEnrich);
}
