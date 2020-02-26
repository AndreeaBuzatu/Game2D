package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;

public abstract class TimedBulletEffect extends BulletEffect {
    private final Time duration;

    Time elapsedTime;

    TimedBulletEffect(Time duration) {
        this.duration = duration;

        elapsedTime = Time.ZERO;

        isDead = false;
    }

    public void update(Time deltaTime) {
        elapsedTime = Time.add(elapsedTime, deltaTime);

        if (elapsedTime.asSeconds() > duration.asSeconds()) {
            elapsedTime = Time.ZERO;

            isDead = true;
        }
    }
}
