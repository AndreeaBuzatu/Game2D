package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public abstract class Wave {
    private final Vector2f destination;

    private double vectorDistance;

    final Queue<Entity> toRespawn;

    final Vector2f origin;

    Vector2f direction;

    Wave(Vector2f origin, Vector2f destination) {
        this.origin = origin;
        this.destination = destination;

        toRespawn = new LinkedList<>();
    }

    public abstract void update(Set<Entity> entities, Time deltaTime);

    void calculateMoveToPoint(Vector2f pos) {
        vectorDistance = distance(pos, destination);

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

    float rotateSprite(double waveAngle) {
        float test = getFacingDirection();

        return (float) Math.toDegrees(Math.sin(waveAngle)) + test;
    }

    float getFacingDirection()
    {
        float dx = destination.x - origin.x;
        float dy = destination.y - origin.y;
        float axis = 90f;

        if (dx < 0) {
            axis += 180f;
        }
        float angle = (float) (axis + Math.toDegrees(Math.atan(dy / dx)));

        System.out.println(angle);

        return angle;
    }

    public Queue<Entity> getToRespawn() {
        return toRespawn;
    }

    public Vector2f getOrigin() {
        return origin;
    }
}
