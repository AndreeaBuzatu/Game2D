package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

public abstract class Wave {
    final Vector2f origin;

    Vector2f direction;

    private final Vector2f destination;

    private double vectorDistance;

    Wave(Vector2f origin, Vector2f destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public abstract void update(Entity entity);

    void calculateMoveToPoint() {
        vectorDistance = distance(origin, destination);

        direction = normalise(Vector2f.sub(destination, origin));

      //  System.out.println("Distance: " + vectorDistance + ", Direction: " + direction);
    }

    private double length(Vector2f vector) {
        return Math.sqrt((vector.x * vector.x) + (vector.y + vector.y));
    }

    private double distance(Vector2f left, Vector2f right) {
        return length(Vector2f.sub(right, left));
    }

    boolean pastDestination(Vector2f entityPosition) {
        return distance(origin, entityPosition) >= vectorDistance;
    }

    private Vector2f normalise(Vector2f vec) {
        double length = length(vec);

        return new Vector2f((float) (vec.x / length), (float) (vec.y / length));
    }

    public Vector2f direction(Vector2f left, Vector2f right) {
        return normalise(Vector2f.sub(right, left));
    }
}
