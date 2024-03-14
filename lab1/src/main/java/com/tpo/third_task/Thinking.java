package com.tpo.third_task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@AllArgsConstructor
@Setter
@Getter
public class Thinking implements Runnable {

    private Entity entity;
    private Identity targetIdentity;

    @Override
    public void run() {
        int time = entity.getPosition().getComfort() * 50;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            return;
        }
        entity.setIdentity(targetIdentity);
        entity.finishThinking(Thread.currentThread());
    }
}
