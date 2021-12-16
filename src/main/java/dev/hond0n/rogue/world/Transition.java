package dev.hond0n.rogue.world;

public class Transition<T> {
    T oldState;
    T newState;
    boolean isFinished;

    public Transition(T oldState, T newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.isFinished = false;
    }

    public void finish() {
        isFinished = true;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
