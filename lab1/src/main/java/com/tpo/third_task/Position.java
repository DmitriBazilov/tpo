package com.tpo.third_task;

public enum Position {
    SITTING(90),
    STAYING(60),
    LYING(100),
    CURLED(30);

    private final int comfort;

    private Position(int comfort) {
        this.comfort = comfort;
    }

    public int getComfort() {
        return comfort;
    }
}