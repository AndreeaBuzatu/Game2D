package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class LivesComponent implements Component {
    private final int startingLives;

    private int lives;

    public LivesComponent(int lives) {
        this.lives = lives;

        startingLives = lives;
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

    public void resurrect() {
        lives = startingLives;
    }

    public void kill() {
        lives = 0;
    }
}
