package rl.env;

import java.util.ArrayList;

public class Experience {
    public final State state;
    public final Action action;
    public final double reward;

    public Experience(State state, Action action, double reward){
        this.state = state;
        this.action = action;
        this.reward = reward;
    }
}
