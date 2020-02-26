package uk.ac.lancaster.scc210.game.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public abstract class Pattern {
    private final Time firingGap;

    private Time elapsedTime;

    protected final Entity[] toFire;

    protected final Vector2f[] coords;

    protected final Sprite spaceShipSprite;

    protected final String spawnedEntityName;

    protected final Entity spaceShip;

    protected Pattern(Entity spaceShip, Entity[] toFire, String spawnedEntityName, Time firingGap) {
        this.spaceShip = spaceShip;
        this.toFire = toFire;
        this.spawnedEntityName = spawnedEntityName;
        this.firingGap = firingGap;

        elapsedTime = Time.ZERO;

        coords = new Vector2f[toFire.length];

        spaceShipSprite = ((SpriteComponent) spaceShip.findComponent(SpriteComponent.class)).getSprite();
    }

    public abstract Entity[] create();

    public abstract void position(Sprite toSpawnSprite);

    public Time getFiringGap() {
        return firingGap;
    }

    public Time getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Time elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
