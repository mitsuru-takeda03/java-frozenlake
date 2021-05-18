package rl.env;

public enum Status {
    Start(0),
    Fail(0),
    Success(1);

    private final double reward;

    Status(double reward) { this.reward = reward; }

    public double getReward() { return reward; }
}
