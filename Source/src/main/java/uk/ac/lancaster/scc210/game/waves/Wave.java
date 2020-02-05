package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.Set;

public abstract class Wave {
    private final int MAX_DEGREES = 360;

    private final Vector2f destination;

    private double vectorDistance;

    final Vector2f origin;

    Vector2f direction;

    Wave(Vector2f origin, Vector2f destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public abstract void update(Set<Entity> entities, Time deltaTime);

    void calculateMoveToPoint(Vector2f spritePos) {
        vectorDistance = distance(spritePos, destination);

        direction = normalise(Vector2f.sub(destination, origin));
    }

    private double length(Vector2f vector) {
        return Math.sqrt((vector.x * vector.x) + (vector.y + vector.y));
    }

    private double distance(Vector2f left, Vector2f right) {
        return length(Vector2f.sub(right, left));
    }

    boolean passedDestination(Vector2f entityPosition) {
        return distance(origin, entityPosition) >= vectorDistance;
    }

    private Vector2f normalise(Vector2f vec) {
        double length = length(vec);

        return new Vector2f((float) (vec.x / length), (float) (vec.y / length));
    }

    float rotateSprite() {
        return (float) Math.toDegrees(Math.atan2(-destination.y - -origin.y, destination.x - origin.x)) + 180;
    }
}
