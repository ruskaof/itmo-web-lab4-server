package com.ruskaof.itmoweblab4server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruskaof.itmoweblab4server.model.Attempt;

import java.util.List;

public class AttemptListWithOffsetDTO {
    @JsonProperty("attempts")
    private List<Attempt> attempts;
    @JsonProperty("has_more")
    private boolean hasMore;

    public AttemptListWithOffsetDTO(List<Attempt> attempts, boolean attemptsCount) {
        this.attempts = attempts;
        this.hasMore = attemptsCount;
    }


    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public boolean getAttemptsCount() {
        return hasMore;
    }

    public void setAttemptsCount(boolean attemptsCount) {
        this.hasMore = attemptsCount;
    }


    @Override
    public String toString() {
        return "AttemptListWithOffsetDTO{" +
                "attempts=" + attempts +
                ", hasMore=" + hasMore +
                '}';
    }
}

