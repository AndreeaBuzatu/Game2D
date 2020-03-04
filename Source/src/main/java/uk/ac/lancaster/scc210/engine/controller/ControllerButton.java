package uk.ac.lancaster.scc210.engine.controller;

import org.jsfml.window.Joystick;

/**
 * The enum Controller button.
 */
/*
 * Sourced from: www.carvware.com/images/screenshots/XBOX360Controller.png
 * Note: subtract 1 from the controller numbers
 */
public enum ControllerButton {
    /**
     * A button controller button.
     */
    A_BUTTON(0),
    /**
     * B button controller button.
     */
    B_BUTTON(1),
    /**
     * X button controller button.
     */
    X_BUTTON(2),
    /**
     * Y button controller button.
     */
    Y_BUTTON(3),
    /**
     * Left bumper controller button.
     */
    LEFT_BUMPER(4),
    /**
     * Right bumper controller button.
     */
    RIGHT_BUMPER(5),
    /**
     * Right joystick press controller button.
     */
    RIGHT_JOYSTICK_PRESS(6),
    /**
     * Left joystick press controller button.
     */
    LEFT_JOYSTICK_PRESS(7),
    /**
     * Start button controller button.
     */
    START_BUTTON(8),
    /**
     * Back button controller button.
     */
    BACK_BUTTON(9),
    /**
     * Xbox button controller button.
     */
    XBOX_BUTTON(10),
    /**
     * D pad up controller button.
     */
    D_PAD_UP(11),
    /**
     * D pad down controller button.
     */
    D_PAD_DOWN(12),
    /**
     * D pad left controller button.
     */
    D_PAD_LEFT(13),
    /**
     * D pad right controller button.
     */
    D_PAD_RIGHT(14);

    private final int id;

    private final int controllerId;

    ControllerButton(final int id) {
        this.id = id;

        controllerId = Controller.getControllerId();
    }

    /**
     * Is pressed boolean.
     *
     * @return the boolean
     */
    public boolean isPressed() {
        return Controller.hasController(controllerId) && Joystick.isButtonPressed(controllerId, id);
    }
}
