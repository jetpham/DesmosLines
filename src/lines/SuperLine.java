package src.lines;

/**
 * SuperLine is an abstract class that represents any line in the lines package
 */
public abstract class SuperLine {
    /**
     * @return formats line data into a format that is pastable for desmos
     */
    public abstract String lineForDesmos();

    /**
     * @return returns the mirrored line of the line.
     */
    public abstract String mirroredLineForDesmos();
}
