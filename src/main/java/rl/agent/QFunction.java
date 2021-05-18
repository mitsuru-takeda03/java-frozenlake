package rl.agent;

import rl.env.Action;
import rl.env.State;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Q関数
 * Q値のテーブルを保持
 */
public class QFunction {
    private ArrayList<ArrayList<Double>> qTable;
    private final int stateDimension;
    private final int actionDimension;

    public QFunction(int stateDimension, int actionDimension){
        this.stateDimension = stateDimension;
        this.actionDimension = actionDimension;
        reset();
    }

    public void reset(){
        qTable = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i < stateDimension; i++) {
            ArrayList<Double> qLine = new ArrayList<Double>();
            for(int j = 0; j < actionDimension; j++){
                qLine.add(0.);
            }
            qTable.add(qLine);
        }
    }

    public double getQ(State state, Action action) {
        return qTable.get(state.toInt()).get(Action.toInt(action));
    }

    public double getMaxQ(State state){
        return Collections.max(qTable.get(state.toInt()));
    }

    public void setQ(State state, Action action, double q) {
        this.qTable.get(state.toInt()).set(Action.toInt(action), q);
    }

    public ArrayList<ArrayList<Double>> getQTable() { return qTable; }

    public void setQTable(ArrayList<ArrayList<Double>> qTable) { this.qTable = qTable; }
}
