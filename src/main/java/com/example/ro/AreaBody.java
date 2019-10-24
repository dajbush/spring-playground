package com.example.ro;

import javax.validation.constraints.NotNull;

public class AreaBody {
    @NotNull
    private String type;
    private int radius;
    private int width;
    private int height;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "AreaBody{type=" + type + ", radius=" + radius + "}";
    }
}