package com.example.defaultmethods;

public class Apple implements Eatable {
    private int slices;

    public Apple() {
        this.slices = 4;
    }

    @Override
    public void eat() {
        if (this.slices > 0) {
            this.slices--;
        }
    }

    @Override
    public void dispose() {
        this.slices = 0;
    }

    public int getSlicesLeft() {
        return this.slices;
    }
}
