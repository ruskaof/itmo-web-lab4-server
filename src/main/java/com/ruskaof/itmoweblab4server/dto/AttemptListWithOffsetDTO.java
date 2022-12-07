package com.ruskaof.itmoweblab4server.dto;

import com.ruskaof.itmoweblab4server.model.Attempt;

import java.util.List;

public class AttemptListWithOffsetDTO {
    private List<Attempt> attempts;
    private long attemptsCount;

    public AttemptListWithOffsetDTO(List<Attempt> attempts, long attemptsCount) {
        this.attempts = attempts;
        this.attemptsCount = attemptsCount;
    }


    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public long getAttemptsCount() {
        return attemptsCount;
    }

    public void setAttemptsCount(long attemptsCount) {
        this.attemptsCount = attemptsCount;
    }


    @Override
    public String toString() {
        return "AttemptListWithOffsetDTO{" +
                "attempts=" + attempts +
                ", attemptsCount=" + attemptsCount +
                '}';
    }
}

