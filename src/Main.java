package src;

import src.lines.*;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.String.valueOf;
import static java.nio.file.Files.readAllBytes;

public class Main {
    private final static boolean mirrorLines = true;
    private final static String resourcesPath = "C:\\Users\\Jet-Laptop\\Documents\\repos\\DesmosLines\\src\\main\\resources";

    public static BigDecimal[][] getPointTable() {
        String text = "";
        try {
            text = new String(readAllBytes(Paths
                    .get(resourcesPath + "\\points.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<BigDecimal[]> points = new ArrayList<>();
        for (String i : text.split("\n")) {
            BigDecimal[] bigDecimals;
            bigDecimals = new BigDecimal[]{new BigDecimal(valueOf(parseDouble(i.split("\t")[0]))),
                    new BigDecimal(valueOf(parseDouble(i.split("\t")[1])))};
            points.add(bigDecimals);
        }
        BigDecimal[][] pointsArray = {{}};
        pointsArray = points.toArray(pointsArray);
        return pointsArray;
    }

    public static SuperLine RandomLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        int seed = new Random().nextInt(4);
        SuperLine returnedSuperLine = null;
        switch (seed) {
            case 0 -> returnedSuperLine = new LinearLine(oldPoint, newPoint);
            case 1 -> returnedSuperLine = new CubicLine(oldPoint, newPoint);
            case 2 -> returnedSuperLine = new QuadraticLine(oldPoint, newPoint);
            case 3 -> returnedSuperLine = new RootLine(oldPoint, newPoint);
            default -> {
            }
        }
        return returnedSuperLine;
    }

    public static void main(String[] args) {

        ArrayList<SuperLine> lines = new ArrayList<>();
        boolean firstPoint = true;
        BigDecimal[] oldPoint = {};
        BigDecimal[][] pointTable = getPointTable();
        for (BigDecimal[] i : pointTable) {
            BigDecimal[] newPoint = {i[0], i[1]};
            if (!firstPoint) lines.add(RandomLine(oldPoint, newPoint));
            firstPoint = false;
            oldPoint = newPoint;
        }
        SuperLine[] linesArray = {};
        linesArray = lines.toArray(linesArray);
        StringBuilder essay = new StringBuilder();
        for (SuperLine i : linesArray) {
            System.out.println(i.getClass().getSimpleName());
            essay.append(i.lineForDesmos()).append("\n");
            if (!Objects.equals(i.getClass().getSimpleName(), "LinearLine") && mirrorLines) {
                essay.append(i.mirroredLineForDesmos());
                essay.append("\n");
            }
        }

        try {
            FileWriter myWriter;
            myWriter = new FileWriter(
                    resourcesPath + "\\output.txt");
            myWriter.write(essay.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
