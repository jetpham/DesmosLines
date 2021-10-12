package src.lines;

import java.lang.Math;

public class LinearLine {
    private double[] domain = { 0, 0 }; // domain for functions that follow 'y ='
    private double[] range = { 0, 0 }; // range for functions that follow 'x ='
    private double m = 0; // the slope for diagonal lines
    private double b = 0; // the y intercept for 'y =' functions & x values for 'x =' functions
    private boolean posB = true; // Is yInt positive or negative. used for determining how to format 'y = mx +
                                         // b' functions
    private int lineType = 0; // 0: 'y = b', 1: 'x = b', 2: 'y = mx + b'
    private double[] oldPointForWork = {}; // for storing the original points. used later for the linesForNormalWithWork
                                           // function
    private double[] newPointForWork = {};

    public LinearLine(double[] oldPoint, double[] newPoint) {
        oldPointForWork = oldPoint;
        newPointForWork = newPoint;
        if (oldPoint[1] == newPoint[1]) {
            // 'y = b' function
            lineType = 0;
            b = oldPoint[1];
            // setting domain
            if (oldPoint[0] >= newPoint[0]) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
        } else if (oldPoint[0] == newPoint[0]) {
            lineType = 1;
            m = 1;
            b = oldPoint[0];
            // setting range
            if (oldPoint[1] >= newPoint[1]) {
                range[0] = newPoint[1];
                range[1] = oldPoint[1];
            } else {
                range[0] = oldPoint[1];
                range[1] = newPoint[1];
            }
        } else {
            lineType = 2;
            // calculating slope using the (y1-y2)/(x1-x2) formula
            m = (oldPoint[1] - newPoint[1]) / (oldPoint[0] - newPoint[0]);
            // calculating yInt using the b = y - (m * x) formula
            b = oldPoint[1] - (m * oldPoint[0]);
            posB = b >= 0;
            // setting domain
            if (oldPoint[0] >= newPoint[0]) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
        }
    }

    /**
     * use the line type to determine which of the three different type os lines to
     * output. There are horizontal lines, 'x = b' There are vertical lines, 'y = b'
     * There are diagonal lines, 'y = mx + b' These lines all come with domain or
     * range, whichever is appropriate for the type of line. Diagonal lines will
     * always use domain rather than range.
     * <p>
     * curly braces and lesser than and equal too signs require additional
     * characters to be formatted into desmos correctly
     *
     * @return string of the line formatted to be pasted into the desmos expression
     *         lines.
     */
    public String lineForDesmos() {
        String returnedLine = "";
        switch (lineType) {
            case 0: // 'y = b' equation
                returnedLine = "y = " + b + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1] + "\\right\\}";
                break;
            case 1: // 'x = b' equation
                returnedLine = "x = " + b + "\\left\\{" + range[0] + "\\le y\\le" + range[1] + "\\right\\}";
                break;
            case 2: // 'y = mx + b' equation
                if (posB) { // if the y integer is positive. use a + for the mx + b
                    returnedLine = "y = " + m + "x + " + b + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1]
                            + "\\right\\}";
                } else { // else use a - for mx - b and use abs on b. this is for formatting purposes
                    returnedLine = "y = " + m + "x - " + Math.abs(b) + "\\left\\{" + domain[0] + "\\le x\\le"
                            + domain[1] + "\\right\\}";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }

    /**
     * use the line type to determine which of the three different type os lines to
     * output. There are horizontal lines, 'x = b' There are vertical lines, 'y = b'
     * There are diagonal lines, 'y = mx + b' These lines all come with domain or
     * range, whichever is appropriate for the type of line. Diagonal lines will
     * always use domain rather than range.
     *
     * @return string of the line formatted using normal format
     */
    public String lineForNormal() {
        String returnedLine = "";
        switch (lineType) {
            case 0: // 'y = b' equation
                returnedLine = "y = " + b + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                break;
            case 1: // 'x = b' equation
                returnedLine = "x = " + b + " {" + range[0] + "≤ y ≤" + range[1] + "}";
                break;
            case 2: // 'y = mx + b' equation
                if (posB) { // if the y integer is positive. use a + for the mx + b
                    returnedLine = "y = " + m + "x + " + b + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                } else { // else use a - for mx - b and use abs on b. this is for formatting purposes
                    returnedLine = "y = " + m + "x - " + b + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }

    /**
     * use the line type to determine which of the three different type os lines to
     * output. There are horizontal lines, 'x = b' There are vertical lines, 'y = b'
     * There are diagonal lines, 'y = mx + b' These lines all come with domain or
     * range, whichever is appropriate for the type of line. Diagonal lines will
     * always use domain rather than range.
     * <p>
     * The work explanation is made in full sentences and will incorporate the
     * values for making the line. It also demonstrates the calculations and
     * substitutions.
     *
     * @return string of explanation of how to solve for the linear line. The
     *         equation in normal form is included
     */
    public String lineForNormalWithWork() {
        String returnedLine = "";
        switch (lineType) {
            case 0:
                returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                        + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                        + "X values are the same so it is a vertical line with an equation \'x = b\'\n"
                        + "The equation for this line should be \'x = " + b + "\' since both points\' x values are "
                        + b + ".\n"
                        + "Vertical lines should be restricted by range so we can take the lower of the y values, "
                        + domain[0]
                        + ", and use that as the lower bound of the range, we can then use the other y value, "
                        + domain[1] + ", and use that as the upper bound of the range.\n"
                        + "We can then combine the equation and range to get the full equation: \n" + "\'x = " + b
                        + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                break;
            case 1:
                returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                        + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                        + "Y values are the same so it is a horizontal line with an equation \'y = b\'\n"
                        + "The equation for this line should be \'y = " + b + "\' since both points\' y values are "
                        + b + ".\n"
                        + "Horizontal lines should be restricted by domain so we can take the lower of the x values, "
                        + domain[0]
                        + ", and use that as the lower bound of the domain, we can then use the other x value, "
                        + domain[1] + ", and use that as the upper bound of the domain.\n"
                        + "We can then combine the equation and domain to get the full equation: \n" + "\'y = " + b
                        + " {" + range[0] + "≤ y ≤" + range[1] + "}\'";
                break;
            case 2:
                if (posB) {
                    returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                            + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                            + "Y values aren\'t the same and the x values aren\'t the same so it is a diagonal line with an equation \'y = mx + b\'\n"
                            + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'. We can plug in our values: \'slope = (("
                            + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" + oldPointForWork[0] + ")-("
                            + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + m + "\'.\n"
                            + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in these values, using our first point for our x and y values: \' b = ("
                            + oldPointForWork[1] + ") - (" + m + " * " + oldPointForWork[0]
                            + ")\'. We can evaluate this to be \'b = " + b + "\'.\n"
                            + "Using the slope and y intercept, we can formulate that the equation for this linear equation is \'y = "
                            + m + "x + " + b + "\'.\n"
                            + "Diagonal lines are normally restricted by domain so we can take the lower of the x values, "
                            + domain[0]
                            + ", and use that as the lower bound of the domain, we can then use the other x value, "
                            + domain[1] + ", and use that as the upper bound of the domain.\n"
                            + "We can then combine the equation and domain to get the full equation: \n" + "\'y = "
                            + m + "x + " + b + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                } else {
                    returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1] + ") Point 2: ("
                            + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
                            + "Y values aren\'t the same and the x values aren\'t the same so it is a diagonal line with an equation \'y = mx + b\'\n"
                            + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'. We can plug in our values: \'slope = (("
                            + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" + oldPointForWork[0] + ")-("
                            + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + m + "\'.\n"
                            + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in these values, using our first point for our x and y values: \' b = ("
                            + oldPointForWork[1] + ") - (" + m + " * " + oldPointForWork[0]
                            + ")\'. We can evaluate this to be \'b = " + b + "\'.\n"
                            + "Using the slope and y intercept, we can formulate that the equation for this linear equation is \'y = "
                            + m + "x + " + b + "\'.\n"
                            + "Diagonal lines are normally restricted by domain so we can take the lower of the x values, "
                            + domain[0]
                            + ", and use that as the lower bound of the domain, we can then use the other x value, "
                            + domain[1] + ", and use that as the upper bound of the domain.\n"
                            + "We can then combine the equation and domain to get the full equation: \n" + "\'y = "
                            + m + "x - " + b + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
                }
                break;
            default:
                break;
        }
        return returnedLine;
    }
}
