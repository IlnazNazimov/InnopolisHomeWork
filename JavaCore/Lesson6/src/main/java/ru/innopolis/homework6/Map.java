package ru.innopolis.homework6;

import java.util.Arrays;

public class Map {
    private int[][] fields;
    private final int width;
    private final int length;

    public Map(int[][] fields, int width, int length) {
        this.fields = fields;
        this.width = width;
        this.length = length;
    }

    public int[][] getFields() {
        return fields;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setFields(int[][] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "fields=" + Arrays.toString(fields);
    }
}
