package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.bullets.patterns.Pattern;

public class FiringPatternComponent implements Component {
    private  Pattern pattern;

    public FiringPatternComponent(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
