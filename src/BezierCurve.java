package src;

public class BezierCurve {
    private double[] controlPoint1 = {};
    private double[] controlPoint2 = {};
    private double[] controlPoint3 = {};
    private double[] controlPoint4 = {};

    public BezierCurve(double[] controlPoint1, double[] controlPoint2, double[] controlPoint3, double[] controlPoint4) {
        // equation for bezier curve in desmos:
        // \left(\left(1-t\right)\left(\left(1-t\right)\left(\left(1-t\right)x_{0}+tx_{1}\right)+t\left(\left(1-t\right)x_{1}+tx_{2}\right)\right)+t\left(\left(1-t\right)\left(\left(1-t\right)x_{1}+tx_{2}\right)+t\left(\left(1-t\right)x_{2}+tx_{3}\right)\right),\left(1-t\right)\left(\left(1-t\right)\left(\left(1-t\right)y_{0}+ty_{1}\right)+t\left(\left(1-t\right)y_{1}+ty_{2}\right)\right)+t\left(\left(1-t\right)\left(\left(1-t\right)y_{1}+ty_{2}\right)+t\left(\left(1-t\right)y_{2}+ty_{3}\right)\right)\right)
        this.controlPoint1 = controlPoint1;
        this.controlPoint2 = controlPoint2;
        this.controlPoint3 = controlPoint3;
        this.controlPoint4 = controlPoint4;
    }

    /**
     * @return string of the curve formatted to be pasted into the desmos expression
     *         lines.
     */
    public String lineForDesmos() {
        String returnedLine = "";
        returnedLine = "\\left(\\left(1 - t\\right)\\left(\\left(1 - t\\right)\\left(\\left(1 - t\\right)"
                + controlPoint1[0] + " + t" + controlPoint2[0] + "\\right) + t\\left(\\left(1 - t\\right)"
                + controlPoint2[0] + " + t" + controlPoint3[0]
                + "\\right)\\right) + t\\left(\\left(1 - t\\right)\\left(\\left(1 - t\\right)" + controlPoint2[0]
                + " + t" + controlPoint3[0] + "\\right) + t\\left(\\left(1 - t\\right)" + controlPoint3[0] + " + t"
                + controlPoint4[0]
                + "\\right)\\right), \\left(1 - t\\right)\\left(\\left(1 - t\\right)\\left(\\left(1 - t\\right)"
                + controlPoint1[1] + " + t" + controlPoint2[1] + "\\right) + t\\left(\\left(1 - t\\right)"
                + controlPoint2[1] + " + t" + controlPoint3[1]
                + "\\right)\\right) + t\\left(\\left(1 - t\\right)\\left(\\left(1 - t\\right)" + controlPoint2[1]
                + " + t" + controlPoint3[1] + "\\right) + t\\left(\\left(1 - t\\right)" + controlPoint3[1] + " + t"
                + controlPoint4[1] + "\\right)\\right)\\right)";
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
        returnedLine = "((1 - t)((1 - t)((1 - t)" + controlPoint1[0] + " + t" + controlPoint2[0] + ") + t((1 - t)"
                + controlPoint2[0] + " + t" + controlPoint3[0] + ")) + t((1 - t)((1 - t)" + controlPoint2[0] + " + t"
                + controlPoint3[0] + ") + t((1 - t)" + controlPoint3[0] + " + t" + controlPoint4[0]
                + ")), (1 - t)((1 - t)((1 - t)" + controlPoint1[1] + " + t" + controlPoint2[1] + ") + t((1 - t)"
                + controlPoint2[1] + " + t" + controlPoint3[1] + ")) + t((1 - t)((1 - t)" + controlPoint2[1] + " + t"
                + controlPoint3[1] + ") + t((1 - t)" + controlPoint3[1] + " + t" + controlPoint4[1] + ")))";
        return returnedLine;
    }
}
