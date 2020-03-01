package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.states.*;

public class StateManager extends ContentManager<State> {
    /**
     * Instantiates a new State manager.
     */
    public StateManager() {
        super(new MainMenu());

        put("main-menu", new MainMenu());

        put("completion", new Completion());

        put("high-score-list", new HighScoreList());

        put("level-select", new LevelSelect());

        put("pause", new PauseMenu());
    }
}
