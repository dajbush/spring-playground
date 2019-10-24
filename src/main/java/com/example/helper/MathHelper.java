package com.example.helper;

import java.util.Iterator;
import java.util.List;

public class MathHelper {

    public String handleCircleArea(int radius) {
        StringBuilder sb = new StringBuilder();
        return sb
        .append("Area of a circle with a radius of ")
        .append(radius)
        .append(" is ")
        .append(String.format("%.5f", Math.PI*Math.pow(radius, 2)))
        .toString();
    }

    public String handleRectangleArea(int width, int height) {
        StringBuilder sb = new StringBuilder();
        return sb
        .append("Area of a ")
        .append(width)
        .append("x")
        .append(height)
        .append(" rectangle is ")
        .append(Math.multiplyExact(width, height))
        .toString();
    }

    public String handleVolume(int length, int width, int height) {
        StringBuilder sb = new StringBuilder();
        return sb
            .append("The volume of a ")
            .append(length)
            .append("x")
            .append(width)
            .append("x")
            .append(height)
            .append(" rectangle is ")
            .append(length*width*height)
            .toString();
    }

    public String handleSum(List<String> values) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        if(values != null) {
            Iterator<String> i = values.iterator();
            while (i.hasNext()) {
                int value = Integer.parseInt(i.next());
                sum += value;
                if(i.hasNext()) {
                    sb.append(value).append(" + ");
                } else {
                    sb.append(value).append(" = ");
                }
            }
        }
        return sb.append(sum).toString();
    }

    public String handleOperation(String operation, int x, int y) {
        String calc = "";
        if(operation != null) {
            switch (operation) {
                case "add":
                    calc = add(x, y);
                break;
                case "subtract":
                    calc = subtract(x, y);
                    break;
                case "multiply":
                    calc = multiply(x, y);
                    break;
                case "divide":
                    calc = divide(x, y);
                    break;
                default:
                    calc = add(x, y);
            }
            return calc;
        }
        calc = add(x, y);
        return calc;
    }

    public String add(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(x)
                .append(" + ")
                .append(y)
                .append(" = ")
                .append(Math.addExact(x, y))
                .toString();
    }

    public String subtract(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(x)
                .append(" - ")
                .append(y)
                .append(" = ")
                .append(Math.subtractExact(x, y))
                .toString();
    }

    public String multiply(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(x)
                .append(" * ")
                .append(y)
                .append(" = ")
                .append(Math.multiplyExact(x, y))
                .toString();
    }

    public String divide(int x, int y) {
        StringBuilder sb = new StringBuilder();
        return sb
                .append(x)
                .append(" / ")
                .append(y)
                .append(" = ")
                .append(Math.floorDiv(x, y))
                .toString();
    }
    
}