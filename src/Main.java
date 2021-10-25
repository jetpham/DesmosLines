package src;

import src.lines.CubicLine;
import src.lines.LinearLine;
import src.lines.QuadraticLine;
import src.lines.SuperLine;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static BigDecimal[][] getPointTable() {
        String text = "";
        ArrayList<BigDecimal[]> points = new ArrayList<>();
        BigDecimal[][] pointsArray = {{}};
        try {
            text = new String(Files.readAllBytes(Paths
                    .get("C:\\Users\\Jet-Laptop\\Documents\\Repos\\DesmosLines\\src\\main\\resources\\points.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String i : text.split("\n")) {
            BigDecimal[] point = {new BigDecimal(String.valueOf(Double.parseDouble(i.split("\t")[0]))),
                    new BigDecimal(String.valueOf(Double.parseDouble(i.split("\t")[1])))};
            points.add(point);
        }
        pointsArray = points.toArray(pointsArray);
        return pointsArray;
    }

    public static SuperLine RandomLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        double seed = Math.random() * 3;
        if (seed > 2) {
            return new LinearLine(oldPoint, newPoint);
        } else if (seed < 1) {
            return new CubicLine(oldPoint, newPoint);
        } else {
            return new QuadraticLine(oldPoint, newPoint);
        }
    }

    public static void main(String[] args) {
        ArrayList<SuperLine> lines = new ArrayList<>();
        boolean firstPoint = true;
        SuperLine[] linesArray = {};
        BigDecimal[] oldPoint = {};

        for (BigDecimal[] i : getPointTable()) {
            BigDecimal[] newPoint = {i[0], i[1]};
            if (!firstPoint) {
                lines.add(RandomLine(oldPoint, newPoint));
            }
            firstPoint = false;
            oldPoint = newPoint;
        }
        linesArray = lines.toArray(linesArray);
        StringBuilder essay = new StringBuilder();
        for (SuperLine i : linesArray) {
            System.out.println(i.getClass().getSimpleName());
            essay.append(i.lineForDesmos()).append("\n");
            if (i.getClass().getSimpleName() != "LinearLine") {
                essay.append(i.mirroredLineForDesmos()).append("\n");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(
                    "C:\\Users\\Jet-Laptop\\Documents\\Repos\\DesmosLines\\src\\main\\resources\\output.txt");
            myWriter.write(essay.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
