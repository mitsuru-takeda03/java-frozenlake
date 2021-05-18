package rl.env;

import java.util.ArrayList;

public class State {
    private final ArrayList<Integer> value;
    public static final int numberOfState = 16;

    State(ArrayList<Integer> value) {
        // valueの範囲をハードコーディング
        ArrayList<Integer> restrictedValue = value;
        if (value.get(0) < 0)
            restrictedValue.set(0, 0);
        else if (value.get(0) > 3)
            restrictedValue.set(0, 3);
        if (value.get(1) < 0)
            restrictedValue.set(1, 0);
        else if (value.get(1) > 3)
            restrictedValue.set(1, 3);
        this.value = restrictedValue;
    }

    public ArrayList<Integer> getValue() {
        return value;
    }

    public int getValue(int dim) { return value.get(dim); }

    public int toInt() { return value.get(1) * 4 + value.get(0);}

    @Override
    public String toString() { return value.toString(); }
}