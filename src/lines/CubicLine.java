package src.lines;

import java.math.BigDecimal;

public class CubicLine extends SuperLine {
    private final BigDecimal[] domain = {new BigDecimal(0), new BigDecimal(0)}; // domain for functions that follow 'y
    // ='
    private final BigDecimal[] range = {new BigDecimal(0), new BigDecimal(0)}; // domain for functions that follow 'y
    // ='
    private final BigDecimal[] oldPoint;
    private final BigDecimal[] newPoint;
    // private BigDecimal a = new BigDecimal(0); // the slope for diagonal lines
    private BigDecimal h = new BigDecimal(0);
    private BigDecimal k = new BigDecimal(0);
    private boolean isLinear = false;
    private boolean useDomain = true;
    private LinearLine linearLine;
    private String fracA = "";

    /**
     * @param oldPoint This point is the vertex of the quadratic
     * @param newPoint This point is a points that the quadratic will intersect with
     */
    public CubicLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
        if (oldPoint[1].equals(newPoint[1]) || oldPoint[0].equals(newPoint[0])) {
            linearLine = new LinearLine(oldPoint, newPoint);
            isLinear = true;
        } else {
            h = oldPoint[0];
            k = oldPoint[1];
            fracA = asFraction(newPoint[1].subtract(oldPoint[1]), newPoint[0].subtract(oldPoint[0]).pow(3));
        }
        System.out.println("Math.abs(" + oldPoint[0] + " - " + newPoint[0] + ") >= Math.abs(" + oldPoint[1] + " - "
                + newPoint[1] + ")");
        if (oldPoint[0].subtract(newPoint[0]).abs().compareTo(oldPoint[1].subtract(newPoint[1]).abs()) >= 0) {
            System.out.println("true");
            if (oldPoint[0].compareTo(newPoint[0]) >= 0) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
        } else {
            System.out.println("false");
            useDomain = false;
            if (oldPoint[1].compareTo(newPoint[1]) >= 0) {
                range[0] = newPoint[1];
                range[1] = oldPoint[1];
            } else {
                range[0] = oldPoint[1];
                range[1] = newPoint[1];
            }
            domain[0] = oldPoint[0];
        }
    }

    private BigDecimal gcd(BigDecimal a, BigDecimal b) {
        return b.compareTo(new BigDecimal(0)) == 0 ? a : gcd(b, a.remainder(b));
    }

    private String asFraction(BigDecimal a, BigDecimal b) {
        BigDecimal gcd = gcd(a, b);
        return "\\frac{" + a.divide(gcd).toPlainString() + "}{" + b.divide(gcd).toPlainString() + "}";
        // \frac{5}{123}
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
     * lines.
     */
    public String lineForDesmos() {
        String returnedLine;
        if (isLinear) {
            returnedLine = linearLine.lineForDesmos();
        } else {
            if (h.compareTo(new BigDecimal(0)) >= 0 && k.compareTo(new BigDecimal(0)) >= 0) {
                returnedLine = "y = " + fracA + "\\left(x - " + h.toPlainString() + "\\right)^{3} + "
                        + k.toPlainString();
            } else if (h.compareTo(new BigDecimal(0)) >= 0 && k.compareTo(new BigDecimal(0)) < 0) {
                returnedLine = "y = " + fracA + "\\left(x - " + h.toPlainString() + "\\right)^{3} - "
                        + k.abs().toPlainString();
            } else if (h.compareTo(new BigDecimal(0)) < 0 && k.compareTo(new BigDecimal(0)) >= 0) {
                returnedLine = "y = " + fracA + "\\left(x + " + h.abs().toPlainString() + "\\right)^{3} + "
                        + k.toPlainString();
            } else {
                returnedLine = "y = " + fracA + "\\left(x + " + h.abs().toPlainString() + "\\right)^{3} - "
                        + k.abs().toPlainString();
            }
            if (useDomain) {
                returnedLine += " \\left\\{" + domain[0] + "\\le x\\le" + domain[1] + "\\right\\}";
            } else if (oldPoint[0].compareTo(newPoint[0]) < 0) {
                returnedLine += " \\left\\{" + range[0] + "\\le y\\le" + range[1] + "\\right\\} \\left\\{" + domain[0]
                        + "\\le x\\right\\}";
            } else {
                returnedLine += " \\left\\{" + range[0] + "\\le y\\le" + range[1] + "\\right\\} \\left\\{" + domain[0]
                        + "\\ge x\\right\\}";
            }
        }
        return returnedLine;
    }

    public String mirroredLineForDesmos() {
        String returnedLine;
        returnedLine = (new CubicLine(newPoint, oldPoint)).lineForDesmos();
        return returnedLine;
    }
}