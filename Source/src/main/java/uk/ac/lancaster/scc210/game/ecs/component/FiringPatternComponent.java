package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

/**
 * The type Firing pattern component.
 */
public class FiringPatternComponent implements Component {
    private  Pattern pattern;

    /**
     * Instantiates a new Firing pattern component.
     *
     * @param pattern the pattern
     */
    public FiringPatternComponent(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Gets pattern.
     *
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Sets pattern.
     *
     * @param pattern the pattern
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
