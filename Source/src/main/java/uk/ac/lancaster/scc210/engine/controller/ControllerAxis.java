package uk.ac.lancaster.scc210.engine.controller;

import org.jsfml.window.Joystick;

/**
 * The enum Controller axis.
 */
public enum ControllerAxis {
    /**
     * D pad left right controller axis.
     */
    D_PAD_LEFT_RIGHT(Joystick.Axis.POV_X),
    /**
     * D pad up down controller axis.
     */
    D_PAD_UP_DOWN(Joystick.Axis.POV_Y),
    /**
     * Left trigger controller axis.
     */
    LEFT_TRIGGER(Joystick.Axis.R),
    /**
     * Right trigger controller axis.
     */
    RIGHT_TRIGGER(Joystick.Axis.Z),
    /**
     * Right joystick left right controller axis.
     */
    RIGHT_JOYSTICK_LEFT_RIGHT(Joystick.Axis.U),
    /**
     * Right joystick up down controller axis.
     */
    RIGHT_JOYSTICK_UP_DOWN(Joystick.Axis.V),
    /**
     * Left joystick left right controller axis.
     */
    LEFT_JOYSTICK_LEFT_RIGHT(Joystick.Axis.X),
    /**
     * Left joystick up down controller axis.
     */
    LEFT_JOYSTICK_UP_DOWN(Joystick.Axis.Y);

    /**
     * The constant AXIS_POSITIVE_THRESHOLD.
     */
// Most axises will register themselves somewhere around -5 to +5 even when not pressed, so let's have a threshold
    public static final float AXIS_POSITIVE_THRESHOLD = 20;

    /**
     * The constant AXIS_NEGATIVE_THRESHOLD.
     */
    public static final float AXIS_NEGATIVE_THRESHOLD = -20;

    private final Joystick.Axis axis;

    private final int controllerId;

    ControllerAxis(Joystick.Axis axis) {
        this.axis = axis;

        controllerId = Controller.getControllerId();
    }

    /**
     * Gets axis position.
     *
     * @return the axis position
     */
    public float getAxisPosition() {
        return Joystick.getAxisPosition(controllerId, axis);
    }

    /**
     * Has axis boolean.
     *
     * @return the boolean
     */
    public boolean hasAxis() {
        return Controller.hasController(controllerId) && Joystick.hasAxis(controllerId, axis);
    }

    /**
     * Axis moved boolean.
     *
     * @return the boolean
     */
    public boolean axisMoved() {
        if (hasAxis()) {
            float axisPosition = getAxisPosition();

            return axisPosition <= AXIS_NEGATIVE_THRESHOLD || axisPosition >= AXIS_POSITIVE_THRESHOLD;

        }

        return false;
    }

    /**
     * Axises moved boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean axisesMoved(ControllerAxis other) {
        return this.axisAcrossThresholds() && other.axisAcrossThresholds();
    }

    private boolean axisAcrossThresholds() {
        float position = this.getAxisPosition();

        return position < ControllerAxis.AXIS_NEGATIVE_THRESHOLD || position > ControllerAxis.AXIS_POSITIVE_THRESHOLD;
    }
}
