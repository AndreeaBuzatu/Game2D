package uk.ac.lancaster.scc210.game.dialogue;

/**
 * The type Line.
 */
public class Line {
    private final String character;

    private String line;

    /**
     * Instantiates a new Line.
     *
     * @param character the character
     * @param line      the line
     */
    public Line(String character, String line) {
        this.character = character;

        this.line = line;

        // First strip any line separators from the text, then replace any instances of > 2 next to each other with a single space
        this.line = line.replaceAll("\\R", " ").replaceAll("\\s{2,}", " ").trim();
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    String getCharacter() {
        return character;
    }

    /**
     * Gets line.
     *
     * @return the line
     */
    String getLine() {
        return line;
    }
}
