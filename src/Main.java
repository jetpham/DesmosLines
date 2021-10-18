package src;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.math.BigDecimal;

import src.lines.*;

public class Main {
    public static BigDecimal[][] getPointTable() {
        String text = "";
        ArrayList<BigDecimal[]> points = new ArrayList<BigDecimal[]>();
        BigDecimal[][] pointsArray = { {} };
        try {
            text = new String(Files.readAllBytes(Paths
                    .get("C:\\Users\\Jet-Laptop\\Documents\\Repos\\DesmosLines\\src\\main\\resources\\points.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String i : text.split("\n")) {
            // System.out.println(i.split("\t")[0]);
            // System.out.println(i.split("\t")[1]);
            BigDecimal[] point = { new BigDecimal(String.valueOf(Double.parseDouble(i.split("\t")[0]))),
                    new BigDecimal(String.valueOf(Double.parseDouble(i.split("\t")[1]))) };
            points.add(point);
        }
        pointsArray = points.toArray(pointsArray);
        return pointsArray;
    }

    public static void main(String[] args) {
        ArrayList<LinearLine> lines = new ArrayList<LinearLine>();
        boolean firstPoint = true;
        LinearLine[] linesArray = {};
        BigDecimal[] oldPoint = {};

        for (BigDecimal[] i : getPointTable()) {
            BigDecimal[] newPoint = { i[0], i[1] };
            if (!firstPoint) {
                lines.add(new LinearLine(oldPoint, newPoint));
            }
            firstPoint = false;
            oldPoint = newPoint;
        }
        linesArray = lines.toArray(linesArray);
        String essay = "";
        // int index = 1;
        for (LinearLine i : linesArray) {
            essay = essay + i.lineForDesmos() + "\n";
            // index++;
        }
        try {
            FileWriter myWriter = new FileWriter(
                    "C:\\Users\\Jet-Laptop\\Documents\\Repos\\DesmosLines\\src\\main\\resources\\output.txt");
            myWriter.write(essay);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
