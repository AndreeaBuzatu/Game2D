package uk.ac.lancaster.scc210.engine.collision;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

public class OrientatedBox {
    private static final int POINTS = 4;

    private Vector2f[] points;

    private Sprite sprite;

    /**
     * Describes a box which accounts for the transformation of the sprite. By default, SFML rectangles do not store the
     * transformation of the rotation. This makes collision checking difficult to implement on transformed rectangles.
     * This fixes that.
     *
     * @param sprite The transformed box of the sprite.
     */
    public OrientatedBox(Sprite sprite) {
        this.sprite = sprite;

        points = new Vector2f[POINTS];
    }

    private void calculatePoints() {
        Transform trans = sprite.getTransform();

        FloatRect local = sprite.getGlobalBounds();

        points[0] = trans.transformPoint(0.f, 0.f);

        points[1] = trans.transformPoint(local.width, 0.f);

        points[2] = trans.transformPoint(local.width, local.height);

        points[3] = trans.transformPoint(0.f, local.height);
    }

    private Vector2f projectOntoAxis(Vector2f axis) {
        // Perform dot product on every axis
        float min = (points[0].x * axis.x + points[0].y * axis.y);

        float max = min;

        for (int j = 1; j < POINTS; j++) {
            float projection = (points[j].x * axis.x + points[j].y * axis.y);

            if (projection < min) {
                min = projection;
            }

            if (projection > max) {
                max = projection;
            }
        }

        return new Vector2f(min, max);
    }

    /**
     * Performs a Separate Axis Theorem collision check between two boxes (including rotation and scaling).
     * This method should be used to account for rotation when performing collision checks between two entities
     * <p>
     * See: https://github.com/SFML/SFML/wiki/Source%3A-Simple-Collision-Detection-for-SFML-2 for the exact implementation
     * of the theorem, this code adapts it for Java and changes the abstraction in an attempt to improve performance.
     *
     * @param left  The first of the bounding boxes to check against
     * @param right The second of the bounding boxes to check against
     * @return if the two boxes are colliding or not
     */
    public static boolean areColliding(OrientatedBox left, OrientatedBox right) {
        left.calculatePoints();

        right.calculatePoints();

        Vector2f[] axes = {
                new Vector2f(left.points[1].x - left.points[0].x, left.points[1].y - left.points[0].y),
                new Vector2f(left.points[1].x - left.points[2].x, left.points[1].y - left.points[2].y),
                new Vector2f(right.points[0].x - right.points[3].x, right.points[0].y - right.points[3].y),
                new Vector2f(right.points[0].x - right.points[1].x, right.points[0].y - right.points[1].y)
        };

        for (int i = 0; i < POINTS; i++) {
            Vector2f leftBoxProjections = left.projectOntoAxis(axes[i]);

            Vector2f rightBoxProjections = right.projectOntoAxis(axes[i]);

            if (!((rightBoxProjections.x <= leftBoxProjections.y) && (rightBoxProjections.y >= leftBoxProjections.x))) {
                return false;
            }
        }

        return true;
    }
}