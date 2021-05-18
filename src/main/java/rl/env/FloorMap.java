package rl.env;

import java.util.ArrayList;
import java.util.Arrays;

public class FloorMap {

    private final ArrayList<ArrayList<FloorPanel>> map;
    private final String enter = System.lineSeparator();

    public FloorMap() {
        // SOOO
        // OXOX
        // OOOX
        // XOOG
        map = new ArrayList<ArrayList<FloorPanel>>();
        map.add(new ArrayList<FloorPanel>(Arrays.asList(FloorPanel.Start, FloorPanel.Normal, FloorPanel.Normal, FloorPanel.Normal)));
        map.add(new ArrayList<FloorPanel>(Arrays.asList(FloorPanel.Normal, FloorPanel.Hole, FloorPanel.Normal, FloorPanel.Hole)));
        map.add(new ArrayList<FloorPanel>(Arrays.asList(FloorPanel.Normal, FloorPanel.Normal, FloorPanel.Normal, FloorPanel.Hole)));
        map.add(new ArrayList<FloorPanel>(Arrays.asList(FloorPanel.Hole, FloorPanel.Normal, FloorPanel.Normal, FloorPanel.Goal)));
    }

    public FloorPanel at(State state){
        return map.get(state.getValue().get(1)).get(state.getValue().get(0));
    }

    @Override
    public String toString(){
        String string = "";
        for(ArrayList<FloorPanel> line : map){
            string += "----------" + enter;
            for(FloorPanel panel : line){
                string += "|" + panel.toString();
            }
            string += "|" + enter;
        }
        string += "----------" + enter;
        return string;
    }
}
