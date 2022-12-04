package com.ruskaof.itmoweblab4server.dto;


public class AttemptDTO {
    private double x;
    private double y;
    private double r;


    public AttemptDTO(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "AttemptDTO{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}