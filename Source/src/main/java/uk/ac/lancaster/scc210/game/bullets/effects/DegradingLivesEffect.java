package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;

public class DegradingLivesEffect extends LivesEffect {
    public DegradingLivesEffect(Time degradeTime) {
        super((int) degradeTime.asSeconds());
    }
}
