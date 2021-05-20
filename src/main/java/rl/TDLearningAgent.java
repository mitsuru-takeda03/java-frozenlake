package rl;

import modules.CSVHandler;
import rl.agent.ELAgent;
import rl.agent.QFunction;
import rl.env.*;

import java.util.ArrayList;

public class TDLearningAgent extends ELAgent {
    private double gamma = 0.2;
    private double learningRate = 0.2;

    public static void main(String[] args) {
        TDLearningAgent tdLearningAgent = new TDLearningAgent();
        Environment environment = new Environment();
        tdLearningAgent.learn(environment, 10000);
    }

    @Override
    public void learn(Environment environment, int maxEpisode) {
        initLogReward();
        qFunction.reset();
        CSVHandler.exportCSV("src/main/resources/TDLearningAgentQ.csv", qFunction.getQTable());
        QFunction nTimes = new QFunction(State.numberOfState, Action.numberOfAction);

        for (int episodeCount = 0; episodeCount < maxEpisode; episodeCount++) {
            // エピソードの終端まで行動する
            environment.reset();
            ArrayList<Experience> experiences = new ArrayList<Experience>();
            while (environment.getStatus() == Status.Start) {
                State state = environment.getState();
                Action action = policy(state, environment.getActionOptions());
                environment.actAgent(action);
                //Temporal differenceによるQ関数の更新
                double gain = environment.getReward() + gamma * qFunction.getMaxQ(environment.getState());
                double estimatedGain = qFunction.getQ(state, action);
                double updatedQ = (1 - learningRate) * estimatedGain + learningRate * gain;
                qFunction.setQ(state, action, updatedQ);
            }
            addLogReward(environment.getReward());
        }
        CSVHandler.exportCSV("src/main/resources/TDLearningAgentQ.csv", qFunction.getQTable());
        CSVHandler.exportCSV("src/main/resources/TDLearningAgentReward.csv", logReward, true);
    }
}
