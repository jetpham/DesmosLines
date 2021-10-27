package src.lines;

import java.math.BigDecimal;

import static java.text.MessageFormat.format;

public class LinearLine extends SuperLine {
    private final BigDecimal[] domain = {new BigDecimal(0), new BigDecimal(0)};
    private final BigDecimal[] range = {new BigDecimal(0), new BigDecimal(0)};
    private final int lineType; // 0: 'y = b', 1: 'x = b', 2: 'y = mx + b'
    private final BigDecimal[] oldPoint;
    private final BigDecimal[] newPoint;
    private String fracM = "";
    private BigDecimal b;
    private String fracB = ""; // the y intercept for 'y =' functions & x values for 'x =' functions

    public LinearLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        this.newPoint = newPoint;
        this.oldPoint = oldPoint;
        if (oldPoint[1].equals(newPoint[1])) {
            // 'y = b' function
            lineType = 0;
            b = oldPoint[1];
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
            if (oldPoint[1].compareTo(newPoint[1]) >= 0) {
                range[0] = newPoint[1];
                range[1] = oldPoint[1];
            } else {
                range[0] = oldPoint[1];
                range[1] = newPoint[1];
            }
        } else {
            lineType = 2;
            fracM = asFraction(oldPoint[1].subtract(newPoint[1]), (oldPoint[0].subtract(newPoint[0])));
            fracB = asFraction((oldPoint[1].negate().multiply(newPoint[0])).add(oldPoint[0].multiply(newPoint[1])),
                    oldPoint[0].subtract(newPoint[0]));
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
        return (b.compareTo(new BigDecimal(0)) == 0) ? a : gcd(b, a.remainder(b));
    }

    private String asFraction(BigDecimal a, BigDecimal b) {
        BigDecimal gcd = gcd(a, b);
        return format("\\frac'{'{0}'}{'{1}'}'",
                a.divide(gcd).toPlainString(),
                b.divide(gcd).toPlainString());
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
    @Override
    public String lineForDesmos() {
        String returnedLine = "";
        switch (lineType) {
            case 0 -> // 'y = b' equation
                    returnedLine = format("y = {0}\\left\\'{'{1}\\le x\\le{2}\\right\\'}'",
                            b,
                            domain[0],
                            domain[1]);
            case 1 -> // 'x = b' equation
                    returnedLine = format("x = {0}\\left\\'{'{1}\\le y\\le{2}\\right\\'}'",
                            b,
                            range[0],
                            range[1]);
            case 2 -> // 'y = mx + b' equation
                    returnedLine = format("y = {0}x + {1}\\left\\'{'{2}\\le x\\le{3}\\right\\'}'",
                            fracM,
                            fracB,
                            domain[0],
                            domain[1]);
            default -> {
            }
        }
        return returnedLine;
    }

    @Override
    public String mirroredLineForDesmos() {
        return (new LinearLine(newPoint, oldPoint)).lineForDesmos();
    }
}
