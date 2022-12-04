package com.ruskaof.itmoweblab4server.dto;

import com.ruskaof.itmoweblab4server.model.Attempt;

import java.util.List;

public class AttemptListWithOffsetDTO {
    private List<Attempt> attempts;
    private long attemptsCount;
    private long offset;

    public AttemptListWithOffsetDTO(List<Attempt> attempts, long attemptsCount, long offset) {
        this.attempts = attempts;
        this.attemptsCount = attemptsCount;
        this.offset = offset;
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

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "AttemptListWithOffsetDTO{" +
                "attempts=" + attempts +
                ", attemptsCount=" + attemptsCount +
                '}';
    }
}

