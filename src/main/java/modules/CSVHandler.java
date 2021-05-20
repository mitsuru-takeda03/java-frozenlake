package modules;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    public static ArrayList<ArrayList<Double>> loadCSV(String path) {
        ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String line : lines) {
                ArrayList<Double> dataLine = new ArrayList<Double>();
                for (String string : line.split(",", 0)){
                    dataLine.add(Double.parseDouble(string));
                }
                data.add(dataLine);
            }
        }
        catch (IOException e) {
            System.out.println("pathが存在しません。");
            e.printStackTrace();
        }
        return data;
    }

    public static void exportCSV(String path, ArrayList<ArrayList<Double>> data){
        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            for (ArrayList<Double> dataLine : data){
                for (double d : dataLine){
                    pw.print(d);
                    pw.print(",");
                }
                pw.println();
            }
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void exportCSV(String path, ArrayList<Double> data, boolean isConvert){
        if(isConvert) {
            ArrayList<ArrayList<Double>> data2 = new ArrayList<>();
            data2.add(data);
            exportCSV(path, data2);
        }
    }
}
