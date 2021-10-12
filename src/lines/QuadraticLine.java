package src.lines;

import java.lang.Math;

public class QuadraticLine {
    /*
     * TODO: finish the quadratic line equations and then format the lines for
     * normal and lines for desmos methods.
     */
    private double[] domain = { 0, 0 }; // domain for functions that follow 'y ='
    private double[] range = { 0, 0 }; // range for functions that follow 'x ='
    private double a = 0; // the slope for diagonal lines
    private double h = 0;
    private double k = 0; // the y intercept for 'y =' functions & x values for 'x =' functions
    private boolean positiveYInt = true; // Is yInt positive or negative. used for determining how to format 'y = mx +
                                         // b' functions
    private boolean isLinear = false;
    private LinearLine linearLine;

    /**
     *
     * @param oldPoint This point is the vertex of the quadratic
     * @param newPoint This point is a points that the quadratic will intersect with
     */
    public QuadraticLine(double[] oldPoint, double[] newPoint) {
        if (oldPoint[1] == newPoint[1] || oldPoint[0] == newPoint[0]) {
            linearLine = new LinearLine(oldPoint, newPoint);
            isLinear = true;
        } else {
            h = oldPoint[0];
            k = oldPoint[1];
            // calculating slope using the (y - k)/(x - h) formula
            a = (newPoint[1] - k) / (newPoint[0] - h);
            positiveYInt = k >= 0;
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
        if (isLinear) {
            returnedLine = linearLine.lineForDesmos();
        } else {
            if (h >= 0 && k >= 0) {
                returnedLine = "y = " + a + "//left(x - " + h + "\\right) + " + k + " \\left{" + domain[0]
                        + "\\le x\\le" + domain[1] + "\\right}";
            } else if (h >= 0 && k < 0) {
                returnedLine = "y = " + a + "//left(x - " + h + "\\right) - " + Math.abs(k) + " \\left{" + domain[0]
                        + "\\le x\\le" + domain[1] + "\\right}";
            } else if (h < 0 && k >= 0) {
                returnedLine = "y = " + a + "//left(x + " + Math.abs(h) + "\\right) + " + k + " \\left{" + domain[0]
                        + "\\le x\\le" + domain[1] + "\\right}";
            } else {
                returnedLine = "y = " + a + "//left(x + " + Math.abs(h) + "\\right) - " + Math.abs(k) + " \\left{"
                        + domain[0] + "\\le x\\le" + domain[1] + "\\right}";
            }
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
        if (isLinear) {
            returnedLine = linearLine.lineForNormal();
        } else {
            if (h >= 0 && k >= 0) {
                returnedLine = "y = " + a + "(x - " + h + ") + " + k + " {" + domain[0] + "≤ x ≤" + domain[1] + "}";
            } else if (h >= 0 && k < 0) {
                returnedLine = "y = " + a + "(x - " + h + ") - " + Math.abs(k) + " {" + domain[0] + "≤ x ≤" + domain[1]
                        + "}";
            } else if (h < 0 && k >= 0) {
                returnedLine = "y = " + a + "(x + " + Math.abs(h) + ") + " + k + " {" + domain[0] + "≤ x ≤" + domain[1]
                        + "}";
            } else {
                returnedLine = "y = " + a + "(x + " + Math.abs(h) + ") - " + Math.abs(k) + " {" + domain[0] + "≤ x ≤"
                        + domain[1] + "}";
            }
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
    // public String lineForNormalWithWork() {
    // String returnedLine = "";
    // switch (lineType) {
    // case 0:
    // returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1]
    // + ") Point 2: ("
    // + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
    // + "X values are the same so it is a vertical line with an equation \'x =
    // b\'\n"
    // + "The equation for this line should be \'x = " + k + "\' since both points\'
    // x values are "
    // + k + ".\n"
    // + "Vertical lines should be restricted by range so we can take the lower of
    // the y values, "
    // + domain[0]
    // + ", and use that as the lower bound of the range, we can then use the other
    // y value, "
    // + domain[1] + ", and use that as the upper bound of the range.\n"
    // + "We can then combine the equation and range to get the full equation: \n" +
    // "\'x = " + k
    // + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
    // break;
    // case 1:
    // returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1]
    // + ") Point 2: ("
    // + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
    // + "Y values are the same so it is a horizontal line with an equation \'y =
    // b\'\n"
    // + "The equation for this line should be \'y = " + k + "\' since both points\'
    // y values are "
    // + k + ".\n"
    // + "Horizontal lines should be restricted by domain so we can take the lower
    // of the x values, "
    // + domain[0]
    // + ", and use that as the lower bound of the domain, we can then use the other
    // x value, "
    // + domain[1] + ", and use that as the upper bound of the domain.\n"
    // + "We can then combine the equation and domain to get the full equation: \n"
    // + "\'y = " + k
    // + " {" + range[0] + "≤ y ≤" + range[1] + "}\'";
    // break;
    // case 2:
    // if (positiveYInt) {
    // returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1]
    // + ") Point 2: ("
    // + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
    // + "Y values aren\'t the same and the x values aren\'t the same so it is a
    // diagonal line with an equation \'y = mx + b\'\n"
    // + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'.
    // We can plug in our values: \'slope = (("
    // + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" +
    // oldPointForWork[0] + ")-("
    // + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + a + "\'.\n"
    // + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation
    // to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in
    // these values, using our first point for our x and y values: \' b = ("
    // + oldPointForWork[1] + ") - (" + a + " * " + oldPointForWork[0]
    // + ")\'. We can evaluate this to be \'b = " + k + "\'.\n"
    // + "Using the slope and y intercept, we can formulate that the equation for
    // this linear equation is \'y = "
    // + a + "x + " + k + "\'.\n"
    // + "Diagonal lines are normally restricted by domain so we can take the lower
    // of the x values, "
    // + domain[0]
    // + ", and use that as the lower bound of the domain, we can then use the other
    // x value, "
    // + domain[1] + ", and use that as the upper bound of the domain.\n"
    // + "We can then combine the equation and domain to get the full equation: \n"
    // + "\'y = "
    // + a + "x + " + k + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
    // } else {
    // returnedLine = "Point 1: (" + oldPointForWork[0] + ", " + oldPointForWork[1]
    // + ") Point 2: ("
    // + newPointForWork[0] + ", " + newPointForWork[1] + ")\n"
    // + "Y values aren\'t the same and the x values aren\'t the same so it is a
    // diagonal line with an equation \'y = mx + b\'\n"
    // + "We can calculate slope by using the equation slope = \'(y1-y2)/(x1-y2)\'.
    // We can plug in our values: \'slope = (("
    // + oldPointForWork[1] + ")-(" + newPointForWork[1] + ")) / ((" +
    // oldPointForWork[0] + ")-("
    // + newPointForWork[0] + "))\' and evaluate it to be \'slope = " + a + "\'.\n"
    // + "We can calculate the y intercept by shuffling the \'y = mx + b\' equation
    // to solve for \'b\'. To solve for \'b\', \'b = y - (m * x)\'. We can plug in
    // these values, using our first point for our x and y values: \' b = ("
    // + oldPointForWork[1] + ") - (" + a + " * " + oldPointForWork[0]
    // + ")\'. We can evaluate this to be \'b = " + k + "\'.\n"
    // + "Using the slope and y intercept, we can formulate that the equation for
    // this linear equation is \'y = "
    // + a + "x + " + k + "\'.\n"
    // + "Diagonal lines are normally restricted by domain so we can take the lower
    // of the x values, "
    // + domain[0]
    // + ", and use that as the lower bound of the domain, we can then use the other
    // x value, "
    // + domain[1] + ", and use that as the upper bound of the domain.\n"
    // + "We can then combine the equation and domain to get the full equation: \n"
    // + "\'y = "
    // + a + "x - " + k + " {" + domain[0] + "≤ x ≤" + domain[1] + "}\'";
    // }
    // break;
    // default:
    // break;
    // }
    // return returnedLine;
    // }
}
