package src.lines;

import java.math.BigDecimal;

import static java.text.MessageFormat.format;

public class CubicRootLine extends SuperLine {
    private final BigDecimal[] domain = {new BigDecimal(0), new BigDecimal(0)};
    private final BigDecimal[] range = {new BigDecimal(0), new BigDecimal(0)};
    private final BigDecimal[] oldPoint;
    private final BigDecimal[] newPoint;
    private BigDecimal h = new BigDecimal(0);
    private BigDecimal k = new BigDecimal(0);
    private boolean isLinear = false;
    private boolean useDomain = true;
    private LinearLine linearLine;
    private String fracA = "";

    public CubicRootLine(BigDecimal[] oldPoint, BigDecimal[] newPoint) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
        if (oldPoint[1].equals(newPoint[1]) || oldPoint[0].equals(newPoint[0])) {
            linearLine = new LinearLine(oldPoint, newPoint);
            isLinear = true;
        } else {
            if ((newPoint[0].subtract(oldPoint[0])).compareTo(new BigDecimal(0)) >= 0) {
                fracA = asFraction(newPoint[1].subtract(oldPoint[1]), newPoint[0].subtract(oldPoint[0]));
                h = oldPoint[0];
                k = oldPoint[1];
            } else {
                fracA = asFraction(oldPoint[1].subtract(newPoint[1]), oldPoint[0].subtract(newPoint[0]));
                h = newPoint[0];
                k = newPoint[1];
            }
        }
        if (oldPoint[0].subtract(newPoint[0]).abs().compareTo(oldPoint[1].subtract(newPoint[1]).abs()) >= 0) {
            if (oldPoint[0].compareTo(newPoint[0]) >= 0) {
                domain[0] = newPoint[0];
                domain[1] = oldPoint[0];
            } else {
                domain[0] = oldPoint[0];
                domain[1] = newPoint[0];
            }
        } else {
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
        return (b.compareTo(new BigDecimal(0)) == 0) ? a : gcd(b, a.remainder(b));
    }

    private String asFraction(BigDecimal a, BigDecimal b) {
        BigDecimal gcd = gcd(a, b);
        System.out.println(a.divide(gcd).toPlainString());
        System.out.println(b.toPlainString());
        System.out.println(b.divide(gcd).toPlainString());
        if ("1".equals(a.divide(gcd).toPlainString())){
            return format("\\frac'{'\\sqrt[3]'{'{0}'}^{2}}{'{1}'}'",
                    b.toPlainString(),
                    b.divide(gcd).toPlainString());
        } else if ("1".equals(b.divide(gcd).toPlainString())){
            return format("{0}",
                    a.divide(gcd).toPlainString());
        }else{
            return format("\\frac'{'{0}\\sqrt[3]'{'{1}'}^{2}}{'{2}'}'",
                a.divide(gcd).toPlainString(),
                b.toPlainString(),
                b.divide(gcd).toPlainString());}
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
        String returnedLine;
        if (isLinear) {
            returnedLine = linearLine.lineForDesmos();
        } else {
            if (h.compareTo(new BigDecimal(0)) >= 0 && k.compareTo(new BigDecimal(0)) >= 0) {
                returnedLine = format("y = {0}\\sqrt[3]'{'x - {1}'}' + {2}",
                        fracA,
                        h.toPlainString(),
                        k.toPlainString());
            } else if (h.compareTo(new BigDecimal(0)) >= 0 && k.compareTo(new BigDecimal(0)) < 0) {
                returnedLine = format("y = {0}\\sqrt[3]'{'x - {1}'}' - {2}",
                        fracA,
                        h.toPlainString(),
                        k.abs().toPlainString());
            } else if (h.compareTo(new BigDecimal(0)) < 0 && k.compareTo(new BigDecimal(0)) >= 0) {
                returnedLine = format("y = {0}\\sqrt[3]'{'x + {1}'}' + {2}",
                        fracA,
                        h.abs().toPlainString(),
                        k.toPlainString());
            } else {
                returnedLine = format("y = {0}\\sqrt[3]'{'x + {1}'}' - {2}",
                        fracA,
                        h.abs().toPlainString(),
                        k.abs().toPlainString());
            }
            if (useDomain) {
                returnedLine += format(" \\left\\'{'{0}\\le x\\le{1}\\right\\'}'",
                        domain[0],
                        domain[1]);
            } else if (oldPoint[0].compareTo(newPoint[0]) < 0) {
                returnedLine += format(" \\left\\'{'{0}\\le y\\le{1}\\right\\'}' \\left\\'{'{2}\\le x\\right\\'}'",
                        range[0],
                        range[1],
                        domain[0]);
            } else {
                returnedLine += format(" \\left\\'{'{0}\\le y\\le{1}\\right\\'}' \\left\\'{'{2}\\ge x\\right\\'}'",
                        range[0],
                        range[1],
                        domain[0]);
            }
        }
        return returnedLine;
    }

    @Override
    public String mirroredLineForDesmos() {
        return (new CubicLine(oldPoint, newPoint).lineForDesmos());
    }
}