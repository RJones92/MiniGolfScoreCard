package com.golf.two_for_tom_open.model.dto.stat;

public abstract class AbstractStat implements Stat {

    private final String statName;
    private final long statValue;

    protected AbstractStat(String statName, long statValue) {
        this.statName = statName;
        this.statValue = statValue;
    }

    @Override
    public String getStatName() {
        return this.statName;
    }

    @Override
    public long getStatValue() {
        return this.statValue;
    }

}
