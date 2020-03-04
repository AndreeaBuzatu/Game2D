package uk.ac.lancaster.scc210.engine.controller;

import org.jsfml.window.Joystick;

/**
 * The type Controller.
 */
public class Controller {
    private static final int UNKNOWN_CONTROLLER = -1;

    // From what I understand SFML detects the XBox360 Controller as having (at least) 11 buttons
    private static final int XBOX_BUTTONS = 11;

    /**
     * Gets controller id.
     *
     * @return the controller id
     */
    static int getControllerId() {
        for (int i = 0; i < Joystick.JOYSTICK_COUNT; i++) {
            if (Joystick.isConnected(i) && Joystick.getButtonCount(i) >= XBOX_BUTTONS) {
                return i;
            }
        }

        return UNKNOWN_CONTROLLER;
    }

    /**
     * Has controller boolean.
     *
     * @return the boolean
     */
    public static boolean hasController() {
        return getControllerId() > UNKNOWN_CONTROLLER;
    }

    /**
     * Has controller boolean.
     *
     * @param controllerID the controller id
     * @return the boolean
     */
    static boolean hasController(final int controllerID) {
        return controllerID > UNKNOWN_CONTROLLER;
    }
}
