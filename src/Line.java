package src;

import java.lang.Math;

public class Line {
    private double[] domain = { 0, 0 };
    private double[] range = { 0, 0 };
    private double slope = 0;
    private double yInt = 0;
    private boolean positiveYInt = true;
    private int lineType = 0;
    double[] oldPointForWork = {};
    double[] newPointForWork = {};

    public Line(double[] oldPoint, double[] newPoint) {
        oldPointForWork = oldPoint;
        newPointForWork = newPoint;
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
                returnedLine = "y = " + yInt + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                break;
            case 1:
                returnedLine = "x = " + yInt + " {" + range[0] + "≤ y ≤" + range[1] + "}";
                break;
            case 2:
                if (positiveYInt) {
                    returnedLine = "y = " + slope + "x + " + yInt + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                } else {
                    returnedLine = "y = " + slope + "x - " + yInt + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }

    public String lineForNormalWithWork() {
        String returnedLine = "";
        switch (lineType) {
            case 0:
                returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                        + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                        + "X values are the same so it is a vertical line with an equation \'x = b\'\n"
                        + "The equation for this line should be \'x = " + yInt + "\' since both points\' x values are "
                        + yInt + ".\n"
                        + "Vertical lines should be restricted by range so we can take the lower of the y values, "
                        + domain[0]
                        + ", and use that as the lower bound of the range, we can then use the other y value, "
                        + domain[1] + ", and use that as the upper bound of the range.\n"
                        + "We can then combine the equation and range to get the full equation: \n" + "\'x = " + yInt
                        + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                break;
            case 1:
                returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                        + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                        + "Y values are the same so it is a horizontal line with an equation \'y = b\'\n"
                        + "The equation for this line should be \'y = " + yInt + "\' since both points\' y values are "
                        + yInt + ".\n"
                        + "Horizontal lines should be restricted by domain so we can take the lower of the x values, "
                        + domain[0]
                        + ", and use that as the lower bound of the domain, we can then use the other x value, "
                        + domain[1] + ", and use that as the upper bound of the domain.\n"
                        + "We can then combine the equation and domain to get the full equation: \n" + "\'y = " + yInt
                        + " {" + range[0] + "≤ y ≤" + range[1] + "}\'";
                break;
            case 2:
                if (positiveYInt) {
                    returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                            + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                            + "Y values aren\'t the same and the x values aren\'t the same so it is a diagonal line with an equation \'y = mx + b\'\n"
                            + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'. We can plug in our values: \'slope = (("
                            + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" + oldPointForWork[0] + ")-("
                            + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + slope + "\'.\n"
                            + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in these values, using our first point for our x and y values: \' b = ("
                            + oldPointForWork[1] + ") - (" + slope + " * " + oldPointForWork[0]
                            + ")\'. We can evaluate this to be \'b = " + yInt + "\'.\n"
                            + "Using the slope and y intercept, we can formulate that the equation for this linear equation is \'y = "
                            + slope + "x + " + yInt + "\'.\n"
                            + "Diagonal lines are normally restricted by domain so we can take the lower of the x values, "
                            + domain[0]
                            + ", and use that as the lower bound of the domain, we can then use the other x value, "
                            + domain[1] + ", and use that as the upper bound of the domain.\n"
                            + "We can then combine the equation and domain to get the full equation: \n" + "\'y = "
                            + slope + "x + " + yInt + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                } else {
                    returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                            + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                            + "Y values aren\'t the same and the x values aren\'t the same so it is a diagonal line with an equation \'y = mx + b\'\n"
                            + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'. We can plug in our values: \'slope = (("
                            + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" + oldPointForWork[0] + ")-("
                            + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + slope + "\'.\n"
                            + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in these values, using our first point for our x and y values: \' b = ("
                            + oldPointForWork[1] + ") - (" + slope + " * " + oldPointForWork[0]
                            + ")\'. We can evaluate this to be \'b = " + yInt + "\'.\n"
                            + "Using the slope and y intercept, we can formulate that the equation for this linear equation is \'y = "
                            + slope + "x + " + yInt + "\'.\n"
                            + "Diagonal lines are normally restricted by domain so we can take the lower of the x values, "
                            + domain[0]
                            + ", and use that as the lower bound of the domain, we can then use the other x value, "
                            + domain[1] + ", and use that as the upper bound of the domain.\n"
                            + "We can then combine the equation and domain to get the full equation: \n" + "\'y = "
                            + slope + "x - " + yInt + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }
}
