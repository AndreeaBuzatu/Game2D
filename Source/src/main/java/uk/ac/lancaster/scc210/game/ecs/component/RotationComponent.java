package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class RotationComponent implements Component {
    public static final float MAX_ROTATION = 365;

    public static final float MIN_ROTATION = 0;

    private float rotationAmount;

    public RotationComponent(final float rotationAmount) {
        this.rotationAmount = rotationAmount;
    }

    public float getRotationAmount() {
        return rotationAmount;
    }
}
