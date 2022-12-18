package com.ruskaof.itmoweblab4server.service.logic;

import com.ruskaof.itmoweblab4server.dto.AddAttemptRequest;
import com.ruskaof.itmoweblab4server.model.Attempt;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttemptConvertor {
    public static Attempt convertToEntity(AddAttemptRequest addAttemptRequest, boolean result) {
        return new Attempt(addAttemptRequest.x(), addAttemptRequest.y(), addAttemptRequest.r(), result);
    }
}
