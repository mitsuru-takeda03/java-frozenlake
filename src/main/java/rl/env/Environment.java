package rl.env;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 強化学習エージェントのテスト環境
 * Frozen Lake
 */
public class Environment {
    private final FloorMap floorMap = new FloorMap();
    private final double slipRate = 0;
    private State state;
    private Status status;
    private int step = 0;

    /**
     * スタート地点(S)に戻る
     */
    public void reset() {
        state = new State(new ArrayList<>(Arrays.asList(0, 0)));
        status = Status.Start;
        step = 0;
    }

    /**
     * 現在の状態で取りうる行動を返す
     * @return 現在の状態で可能なaction
     */
    public ArrayList<Action> getActionOptions() {
        int state_x = state.getValue(0);
        int state_y = state.getValue(1);
        ArrayList<Action> actionOptions = new ArrayList<>(Arrays.asList(Action.Up, Action.Down, Action.Right, Action.Left));
        if (state_x == 0)
            actionOptions.remove(actionOptions.indexOf(Action.Left));
        else if (state_x == 3)
            actionOptions.remove(actionOptions.indexOf(Action.Right));
        if (state_y == 0)
            actionOptions.remove(actionOptions.indexOf(Action.Up));
        else if (state_y == 3)
            actionOptions.remove(actionOptions.indexOf(Action.Down));
        return actionOptions;
    }

    public Status actAgent(Action action) {
        step += 1;
        int isSlip = Math.random() > slipRate ? 1 : -1;
        int state_x = state.getValue(0);
        int state_y = state.getValue(1);
        if (action == Action.Up)
            state_y -= 1 * isSlip;
        else if (action == Action.Down)
            state_y += 1 * isSlip;
        else if (action == Action.Right)
            state_x += 1 * isSlip;
        else if (action == Action.Left)
            state_x -= 1 * isSlip;
        state = new State(new ArrayList<>(Arrays.asList(state_x, state_y)));
        System.out.println(state);
        return getStatus(state);
    }

    public State getState() { return state; }

    public Status getStatus() { return status; }

    public Status getStatus(State state) {
        FloorPanel floorPanelNow = floorMap.at(state);
        if (floorPanelNow == FloorPanel.Hole) {
            status = Status.Fail;
        }
        else if (floorPanelNow == FloorPanel.Goal) {
            status = Status.Success;
        }
        return status;
    }

    public double getReward(){ return status.getReward(); }
}
