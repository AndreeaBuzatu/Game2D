package uk.ac.lancaster.scc210.game.dialogue;

public class Line {
    private final String character;

    private String line;

    public Line(String character, String line) {
        this.character = character;

        this.line = line;

        // First strip any line separators from the text, then replace any instances of > 2 next to each other with a single space
        this.line = line.replaceAll("\\R", " ").replaceAll("\\s{2,}", " ").trim();
    }

    String getCharacter() {
        return character;
    }

    String getLine() {
        return line;
    }
}
