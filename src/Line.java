package src;

import java.lang.Math;

public class Line {
    private double[] domain = { 0, 0 };
    private double[] range = { 0, 0 };
    private double slope = 0;
    private double yInt = 0;
    private boolean positiveYInt = true;
    private int lineType = 0;

    public Line(double[] oldPoint, double[] newPoint) {
        if (oldPoint[1] == newPoint[1]) {
            lineType = 0;
            yInt = oldPoint[1];
            if (oldPoint[0] >= newPoint[0]) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
            // System.out.println("linetype 0: horizontal line");
        } else if (oldPoint[0] == newPoint[0]) {
            lineType = 1;
            slope = 1;
            yInt = oldPoint[0];
            if (oldPoint[1] >= newPoint[1]) {
                range[0] = newPoint[1];
                range[1] = oldPoint[1];
            } else {
                range[0] = oldPoint[1];
                range[1] = newPoint[1];
            }
            // System.out.println("linetype 1: vertical line");
        } else {
            lineType = 2;
            slope = (oldPoint[1] - newPoint[1]) / (oldPoint[0] - newPoint[0]);
            yInt = oldPoint[1] - (slope * oldPoint[0]);
            positiveYInt = yInt >= 0;
            if (oldPoint[0] >= newPoint[0]) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
            // System.out.println("linetype 2: diagnal line");
        }
        // System.out.println("domain: {" + domain[0] + ", " + domain[1] + "}, range: "
        // + range[0] + ", " + range[1]
        // + "}, slope: " + slope + ", yInt: " + yInt);
    }

    public String dumpInfo() {
        return "" + domain + range + slope + yInt + positiveYInt + lineType;
    }

    public String lineForDesmos() {
        String returnedLine = "";
        switch (lineType) {
            case 0:
                returnedLine = "y = " + yInt + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1] + "\\right\\}";
                // System.out.println("lineForDesmos: case 0");
                break;
            case 1:
                returnedLine = "x = " + yInt + "\\left\\{" + range[0] + "\\le y\\le" + range[1] + "\\right\\}";
                // System.out.println("lineForDesmos: case 1");
                break;
            case 2:
                // System.out.println("lineForDesmos: case 2");
                if (positiveYInt) {
                    returnedLine = "y = " + slope + "x + " + yInt + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1]
                            + "\\right\\}";
                    // System.out.println("Positive yInt");
                } else {
                    returnedLine = "y = " + slope + "x - " + Math.abs(yInt) + "\\left\\{" + domain[0] + "\\le x\\le"
                            + domain[1] + "\\right\\}";
                    // System.out.println("Not positive yInt");
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }

    public String lineForNormal() {
        String returnedLine = "";
        switch (lineType) {
            case 0:
                returnedLine = "y = " + yInt + " {" + domain[0] + "<= x <=" + domain[1] + "}";
                break;
            case 1:
                returnedLine = "x = " + yInt + " {" + range[0] + "<= y <=" + range[1] + "}";
                break;
            case 2:
                if (positiveYInt) {
                    returnedLine = "y = " + slope + "x + " + yInt + " {" + domain[0] + "<= x <=" + domain[1] + "}";
                } else {
                    returnedLine = "y = " + slope + "x - " + yInt + " {" + domain[0] + "<= x <=" + domain[1] + "}";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }
}
