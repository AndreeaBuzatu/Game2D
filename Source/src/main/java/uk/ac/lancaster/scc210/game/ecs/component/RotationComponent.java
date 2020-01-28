package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * Component which indicates that an Entity can rotate
 */
public class RotationComponent implements Component {
    public static final float MAX_ROTATION = 360;

    public static final float MIN_ROTATION = 0;

    private final float rotationAmount;

    /**
     * Instantiate RotationComponent
     *
     * @param rotationAmount the amount by which an Entity should rotate by when it rotates
     */
    public RotationComponent(final float rotationAmount) {
        this.rotationAmount = rotationAmount;
    }

    /**
     * Get the rotation amount
     *
     * @return the rotation amount
     */
    public float getRotationAmount() {
        return rotationAmount;
    }
}
