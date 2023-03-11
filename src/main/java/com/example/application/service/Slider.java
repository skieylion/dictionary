package com.example.application.service;

import java.util.function.Consumer;

public class Slider {
    private int current;
    private final int count;

    public enum State {
        START, FINISH, BETWEEN, ONE;
    }

    private Consumer<State> consumer;

    public Slider(int count) {
        this.count = count;
    }

    public void next() {
        if (current < count - 1) {
            current(++current);
        }
    }

    public void prev() {
        if (current > 0) {
            current(--current);
        }
    }

    public Slider change(Consumer<State> consumer) {
        this.consumer = consumer;
        return this;
    }

    public void current(int index) {
        if (index >= 0 && index < count) {
            current = index;
            if (count == 1) {
                consumer.accept(State.ONE);
            } else if (current == 0) {
                consumer.accept(State.START);
            } else if (current == count - 1) {
                consumer.accept(State.FINISH);
            } else if (current < count - 1) {
                consumer.accept(State.BETWEEN);
            }
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
