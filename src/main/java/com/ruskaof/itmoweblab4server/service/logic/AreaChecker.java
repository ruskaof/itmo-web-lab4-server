package com.ruskaof.itmoweblab4server.service.logic;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;

public class AreaChecker {
    private AreaChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean checkArea(AttemptDTO attemptDto) {
        double x = attemptDto.getX();
        double y = attemptDto.getY();
        double r = attemptDto.getR();
        return checkArea(x, y, r);
    }

    public static boolean checkArea(double x, double y, double r) {
        System.out.println("Checking area x = " + x + " y = " + y + " r = " + r);
        return (x <= 0 && y >= 0 && x >= -r / 2. && y <= r ||
                x <= 0 && y <= 0 && x * x + y * y <= r * r ||
                x >= 0 && y <= 0 && y >= 2*x + - r);
    }
}
