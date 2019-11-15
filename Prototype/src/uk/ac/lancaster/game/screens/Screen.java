package uk.ac.lancaster.game.screens;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.event.Event;

public abstract class Screen {
    protected boolean active;

    protected Screen() {
        active = true;
    }

    public abstract void update();

    public abstract void handleEvents(Event event);

    public abstract void draw(RenderTarget target);

    public abstract Screen nextScreen();

    public boolean isFinished() {
        return !active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
