package src;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static double[][] getPointTable() {
        String text = "";
        ArrayList<double[]> points = new ArrayList<double[]>();
        double[][] pointsArray = { {} };
        try {
            text = new String(Files.readAllBytes(
                    Paths.get("C:\\Users\\Jet Pham\\Documents\\repos\\DesmosLines\\src\\main\\resources\\points.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String i : text.split("\n")) {
            double[] point = { Double.parseDouble(i.split("\t")[0]), Double.parseDouble(i.split("\t")[1]) };
            points.add(point);
        }
        pointsArray = points.toArray(pointsArray);
        return pointsArray;
    }

    public static void main(String[] args) {
        ArrayList<LinearLine> lines = new ArrayList<LinearLine>();
        boolean firstPoint = true;
        LinearLine[] linesArray = {};
        double[] oldPoint = {};

        for (double[] i : getPointTable()) {
            double[] newPoint = { i[0], i[1] };
            if (!firstPoint) {
                lines.add(new LinearLine(oldPoint, newPoint));
            }
            firstPoint = false;
            oldPoint = newPoint;
        }
        linesArray = lines.toArray(linesArray);
        String essay = "";
        int index = 1;
        for (LinearLine i : linesArray) {
            essay = essay + "Line " + index + ":\n" + i.lineForNormalWithWork() + "\n\n";
            index++;
        }
        try {
            FileWriter myWriter = new FileWriter(
                    "C:\\Users\\Jet Pham\\Documents\\repos\\DesmosLines\\src\\main\\resources\\output.txt");
            myWriter.write(essay);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
