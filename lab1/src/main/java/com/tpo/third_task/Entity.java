package com.tpo.third_task;

import lombok.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Getter
@Setter
public class Entity {

    private Position position;
    private Identity identity;
    private final Set<Thread> thinks = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private boolean alive;

    public void startIdentify(Identity targetIdentity) {
        Thread thinking = new Thread(new Thinking(this, targetIdentity));
        thinks.add(thinking);
        thinking.start();
    }

    public void finishThinking(Thread think) {
        synchronized (this) {
            think.interrupt();
            thinks.remove(think);
        }
    }

    public void die() {
        if (!alive) throw new IllegalStateException("dead entity can't die");
        position = Position.LYING;
        for (Thread t : thinks) {
            finishThinking(t);
        }
        alive = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return position == entity.position && identity == entity.identity && Objects.equals(thinks, entity.thinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, identity, thinks);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "position=" + position +
                ", identity=" + identity +
                ", thinks=" + thinks +
                '}';
    }
}
