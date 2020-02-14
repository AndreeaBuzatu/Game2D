package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class HealthComponent implements Component {
    private int lives;

    public HealthComponent (int lives)
    {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead()
    {
        return lives < 1;
    }
}
