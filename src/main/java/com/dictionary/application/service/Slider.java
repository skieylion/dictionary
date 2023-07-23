package com.dictionary.application.service;

import java.util.function.BiConsumer;

public class Slider {
    private int current = 0;
    private final int count;

    public enum Direction {
        LEFT, RIGHT, ZERO
    }

    private BiConsumer<Direction, Integer> consumer;

    public Slider(int count) {
        this.count = count;
    }

    public void next() {
        if (current < count - 1) {
            consumer.accept(Direction.RIGHT, ++current);
        }
    }

    public void prev() {
        if (current > 0) {
            consumer.accept(Direction.LEFT, --current);
        }
    }

    public Slider change(BiConsumer<Direction, Integer> consumer) {
        this.consumer = consumer;
        return this;
    }

    public int getCurrentIndex() {
        return current;
    }

    public void init() {
        this.consumer.accept(Direction.ZERO, current);
    }

}
