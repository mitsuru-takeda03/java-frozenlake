package rl.agent;

import java.util.ArrayList;
import java.util.Random;
import rl.env.Action;
import rl.env.Environment;
import rl.env.State;

public abstract class ELAgent {
    protected double epsilon = 0.2;
    protected QFunction qFunction;
    protected ArrayList<Double> logReward;
    protected Action action;
    protected State state;

    protected ELAgent(){
        qFunction = new QFunction(State.numberOfState, Action.numberOfAction);
        logReward = new ArrayList<Double>();
    }

    public void setEpsilon(double epsilon) { this.epsilon = epsilon; }

    public void initLogReward() { logReward = new ArrayList<Double>(); }

    public void addLogReward(double reward) { logReward.add(reward); }

    /**
     * epsilon-greedy
     * @param state
     * @return
     */
    public Action policy(State state, ArrayList<Action> actionOptions){
        if(Math.random() < epsilon){
            Random rand = new Random();
            this.action = actionOptions.get(rand.nextInt(actionOptions.size()));
        }
        else{
            double maxQ = 0;
            Action maxQAction = actionOptions.get(0);
            for(Action action : actionOptions){
                double Q = qFunction.getQ(state, action);
                if(Q > maxQ){
                    maxQ = Q;
                    maxQAction = action;
                }
            }
            this.action = maxQAction;
        }
        return this.action;
    }

    public abstract void learn(Environment environment, int maxEpisode);
}