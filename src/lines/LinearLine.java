package src.lines;

import java.math.BigDecimal;

public class LinearLine extends SuperLine {
    private final BigDecimal[] domain = { new BigDecimal(0), new BigDecimal(0) }; // domain for functions that follow 'y
                                                                                  // ='
    private final BigDecimal[] range = { new BigDecimal(0), new BigDecimal(0) }; // range for functions that follow 'x
                                                                                 // ='
    // b' functions
    private final int lineType; // 0: 'y = b', 1: 'x = b', 2: 'y = mx + b'
    private String fracM = ""; // the slope for diagonal lines
    private BigDecimal b;
    private String fracB = ""; // the y intercept for 'y =' functions & x values for 'x =' functions

    public LinearLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        // for storing the original points. used later for the
        // linesForNormalWithWork
        // function
        if (oldPoint[1].equals(newPoint[1])) {
            // 'y = b' function
            lineType = 0;
            b = oldPoint[1];
            // setting domain
            if (oldPoint[0].compareTo(newPoint[0]) >= 0) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
        } else if (oldPoint[0].equals(newPoint[0])) {
            lineType = 1;
            new BigDecimal(1);
            b = oldPoint[0];
            // setting range
            if (oldPoint[1].compareTo(newPoint[1]) >= 0) {
                range[0] = newPoint[1];
                range[1] = oldPoint[1];
            } else {
                range[0] = oldPoint[1];
                range[1] = newPoint[1];
            }
        } else {
            lineType = 2;
            // calculating slope using the (y1-y2)/(x1-x2) formula
            fracM = asFraction(oldPoint[1].subtract(newPoint[1]), (oldPoint[0].subtract(newPoint[0])));
            // m =
            // oldPoint[1].subtract(newPoint[1]).divide(oldPoint[0].subtract(newPoint[0]));
            // calculating yInt using the b = y - ((y1-y2)/(x1-x2) * x) formula
            // (yx^2-yxw-y+z)/((x-w)x)
            fracB = asFraction((oldPoint[1].negate().multiply(newPoint[0])).add(oldPoint[0].multiply(newPoint[1])),
                    oldPoint[0].subtract(newPoint[0]));
            // posB = (newPoint[1].negate().compareTo(new BigDecimal(0)) >= 0)
            // ^ (oldPoint[0].subtract(newPoint[0]).multiply(oldPoint[0]).compareTo(new
            // BigDecimal(0)) >= 0);
            // setting domain
            if (oldPoint[0].compareTo(newPoint[0]) >= 0) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
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
            returnedLine = "y = " + fracM + "x + " + fracB + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1]
                    + "\\right\\}";
            break;
        default:
            break;
        }
        return returnedLine;
    }

    public String expandedLineForDesmos() {
        String returnedLine = "";
        switch (lineType) {
        case 0: // 'y = b' equation
            returnedLine = "y = " + b + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1] + "\\right\\}";
            break;
        case 1: // 'x = b' equation
            returnedLine = "x = " + b + "\\left\\{" + range[0] + "\\le y\\le" + range[1] + "\\right\\}";
            break;
        case 2: // 'y = mx + b' equation
            returnedLine = "y = " + fracM + "x + " + fracB + "\\left\\{" + domain[0] + "\\le x\\le" + domain[1]
                    + "\\right\\}";
            break;
        default:
            break;
        }
        return returnedLine;
    }

}
