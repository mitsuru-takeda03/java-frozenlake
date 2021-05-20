package rl;

import modules.CSVHandler;
import rl.agent.ELAgent;
import rl.agent.QFunction;
import rl.env.*;

import java.util.ArrayList;

public class MonteCarloAgent extends ELAgent {
    private double gamma = 0.2;

    public static void main(String[] args) {
        MonteCarloAgent monteCarloAgent = new MonteCarloAgent();
        Environment environment = new Environment();
        monteCarloAgent.learn(environment, 10000);
    }

    @Override
    public void learn(Environment environment, int maxEpisode){
        initLogReward();
        qFunction.reset();
        CSVHandler.exportCSV("src/main/resources/MonteCarloAgentQ.csv", qFunction.getQTable());
        QFunction nTimes = new QFunction(State.numberOfState, Action.numberOfAction);

        for(int episodeCount = 0; episodeCount < maxEpisode; episodeCount++){
            // エピソードの終端まで行動する
            environment.reset();
            ArrayList<Experience> experiences = new ArrayList<Experience>();
            while(environment.getStatus()== Status.Start){
                State state = environment.getState();
                Action action = policy(state, environment.getActionOptions());
                environment.actAgent(action);
                experiences.add(new Experience(state, action, environment.getReward()));
            }
            addLogReward(environment.getReward());
            // 行動を評価
            for(Experience ex : experiences){
                double G = 0;
                double t = 0;
                for(int i = 0; i < experiences.size(); i++){
                    G += Math.pow(gamma, t) * experiences.get(i).reward;
                    t += 1;
                }
                nTimes.setQ(ex.state, ex.action, nTimes.getQ(ex.state, ex.action) + 1);
                double alpha = 1 / nTimes.getQ(ex.state, ex.action);
                double pastQ = qFunction.getQ(ex.state, ex.action);
                double newQ = (1 - alpha) * pastQ + alpha * G;
                qFunction.setQ(ex.state, ex.action, newQ);
            }
        }
        CSVHandler.exportCSV("src/main/resources/MonteCarloAgentQ.csv", qFunction.getQTable());
        CSVHandler.exportCSV("src/main/resources/MonteCarloAgentReward.csv", logReward, true);
    }
}
