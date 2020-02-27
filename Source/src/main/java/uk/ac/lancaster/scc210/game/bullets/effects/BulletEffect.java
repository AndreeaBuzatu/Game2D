package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class BulletEffect  {
    final List<Entity> reactedEntities;

    boolean isDead;

    BulletEffect() {
        reactedEntities = new ArrayList<>();

        isDead = false;
    }

    public void react(Entity entity) {
        isDead = false;
    }

    public abstract void reset();

    public abstract void update(Time deltaTime);

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
