package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;

public abstract class PowerUpEffect extends ItemEffect {
    @Override
    public void update(Time deltaTime) {
        isDead = true;
    }
}
