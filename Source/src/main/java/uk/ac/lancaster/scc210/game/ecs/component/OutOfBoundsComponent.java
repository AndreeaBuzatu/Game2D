package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/*
 * Describes an entity which has exceeded the bounds of the window and needs special handling.
 * This is mostly used for entities which have code that runs when removed. In some instances,
 * the behaviour of the code might change if left the bounds instead of something else (i.e. being killed by the player)
 */
public class OutOfBoundsComponent implements Component {
}
