package rl.env;

public enum Action {
    Up("Up"),
    Down("Down"),
    Right("Right"),
    Left("Left"),
    Invalid("Invalid");

    public static final int numberOfAction = 4;
    private final String string;

    Action(String string){ this.string = string; }

    @Override
    public String toString(){ return string; }

    public static Action fromInt(int i) {
        if ( i == 0 ) { return Action.Up; }
        else if ( i == 1 ) { return Action.Down; }
        else if ( i == 2 ) { return Action.Right; }
        else if ( i == 3 ){ return Action.Left; }
        else { return Action.Invalid; }
    }

    public static int toInt(Action Action) {
        if ( Action == Action.Up ) { return 0; }
        else if ( Action == Action.Down ) { return 1; }
        else if ( Action == Action.Right ) { return 2; }
        else if ( Action == Action.Left ){ return 3; }
        else { return 4; }
    }
}
