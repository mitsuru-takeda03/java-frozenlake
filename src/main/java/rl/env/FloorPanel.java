package rl.env;

public enum FloorPanel {
    Start("S"),
    Goal("G"),
    Hole("X"),
    Normal(" ");

    private final String string;

    FloorPanel(String string){ this.string = string; }

    @Override
    public String toString(){ return string; }
}
