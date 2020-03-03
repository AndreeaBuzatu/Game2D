package uk.ac.lancaster.scc210.game.patterns;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public abstract class Pattern {

    private Time firingGap;

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

    public void setFiringGap(Time firingGap) {
        this.firingGap = firingGap;
    }

    protected void positionStarPatterns(Sprite bulletSprite) {
        float startPoint = -bulletSprite.getLocalBounds().width / 2;

        FloatRect localBounds = spaceShipSprite.getLocalBounds();

        float width = localBounds.width;

        float height = localBounds.height;

        float equalDistance = (float) (Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2)) / Math.sqrt(2));

        float difference = (float) (equalDistance * (Math.sqrt(2) - 1) / 2);

        coords[0] = new Vector2f(startPoint, startPoint);

        coords[1] = new Vector2f(startPoint + equalDistance / 2, -difference);

        coords[2] = new Vector2f(startPoint / 2 + equalDistance, startPoint);

        coords[3] = new Vector2f(startPoint + equalDistance + difference, startPoint + equalDistance / 2);

        coords[4] = new Vector2f(equalDistance, equalDistance);

        coords[5] = new Vector2f(-startPoint + equalDistance / 2, equalDistance + difference);

        coords[6] = new Vector2f(-startPoint, width - startPoint);

        coords[7] = new Vector2f(-startPoint - difference, -startPoint + equalDistance / 2);
    }
}
