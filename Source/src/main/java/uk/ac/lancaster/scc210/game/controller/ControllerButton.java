package uk.ac.lancaster.scc210.game.controller;

import org.jsfml.window.Joystick;

/*
 * Sourced from: www.carvware.com/images/screenshots/XBOX360Controller.png
 * Note: subtract 1 from the controller numbers
 */
public enum ControllerButton {
    A_BUTTON(0),
    B_BUTTON(1),
    X_BUTTON(2),
    Y_BUTTON(3),
    LEFT_BUMPER(4),
    RIGHT_BUMPER(5),
    RIGHT_JOYSTICK_PRESS(6),
    LEFT_JOYSTICK_PRESS(7),
    START_BUTTON(8),
    BACK_BUTTON(9),
    XBOX_BUTTON(10),
    D_PAD_UP(11),
    D_PAD_DOWN(12),
    D_PAD_LEFT(13),
    D_PAD_RIGHT(14);

    private final int id;

    ControllerButton(final int id) {
        this.id = id;
    }

    public boolean isPressed() {
        return Joystick.isButtonPressed(Controller.CONTROLLER_ID, id);
    }
}
