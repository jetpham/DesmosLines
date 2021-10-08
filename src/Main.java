package src;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static double[][] getPointTable() {
        String text = "";
        ArrayList<double[]> points = new ArrayList<double[]>();
        double[][] pointsArray = { {} };
        try {
            text = new String(Files.readAllBytes(
                    Paths.get("C:\\Users\\Jet-Laptop\\Desktop\\DesmosLines\\src\\main\\resources\\numbers.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String i : text.split("\n")) {
            double[] point = { Double.parseDouble(i.split("\t")[0]), Double.parseDouble(i.split("\t")[1]) };
            // System.out.println("x: " + i.split("\t")[0] + " --- " +
            // Double.parseDouble(i.split("\t")[0]) + "\ny: "
            // + i.split("\t")[1] + " --- " + Double.parseDouble(i.split("\t")[1]));
            points.add(point);
        }
        pointsArray = points.toArray(pointsArray);
        return pointsArray;
    }

    public static void main(String[] args) {
        ArrayList<Line> lines = new ArrayList<Line>();
        boolean firstPoint = true;
        Line[] linesArray = {};
        double[] oldPoint = {};

        for (double[] i : getPointTable()) {
            System.out.println("(" + i[0] + ", " + i[1] + ")");
            double[] newPoint = { i[0], i[1] };
            if (!firstPoint) {
                lines.add(new Line(oldPoint, newPoint));
                firstPoint = false;
            }
            oldPoint = newPoint;
        }
        linesArray = lines.toArray(linesArray);
        for (Line i : linesArray) {
            System.out.println(i.dumpInfo());
            System.out.println(i.lineForNormal());
        }
    }
}
