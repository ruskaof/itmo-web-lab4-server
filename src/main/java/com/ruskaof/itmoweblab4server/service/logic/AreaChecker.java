package com.ruskaof.itmoweblab4server.service.logic;

import com.ruskaof.itmoweblab4server.dto.AddAttemptRequest;

public class AreaChecker {
    private AreaChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean checkArea(AddAttemptRequest addAttemptRequest) {
        double x = addAttemptRequest.x();
        double y = addAttemptRequest.y();
        double r = addAttemptRequest.r();
        return checkArea(x, y, r);
    }

    public static boolean checkArea(double x, double y, double r) {
        return (x <= 0 && y >= 0 && x >= -r / 2. && y <= r ||
                x <= 0 && y <= 0 && x * x + y * y <= r * r ||
                x >= 0 && y <= 0 && y >= 2 * x + -r);
    }
}
