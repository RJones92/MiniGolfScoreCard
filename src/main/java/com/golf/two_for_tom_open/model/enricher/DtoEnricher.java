package com.golf.two_for_tom_open.model.enricher;

public interface DtoEnricher<T> {

    T enrich(T objectToEnrich);
}
