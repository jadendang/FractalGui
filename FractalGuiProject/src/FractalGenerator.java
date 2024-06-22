import java.awt.Color;
import java.util.ArrayList;

/**
 * The FractalGenerator class generates fractal elements.
 * Implements the FractalSubject interface, allowing observers to attach, detach, and be notified of updates.
 */
public class FractalGenerator implements FractalSubject {
    /**
     * A list of observers that are notified about changes in the fractal data.
     */
    private ArrayList<FractalObserver> observers = new ArrayList<>();
    /**
     * A list of elements in the fractal structure.
     */
    private ArrayList<FractalElement> elements = new ArrayList<>();
    /**
     * The depth of recursion used in the fractal generation process.
     */
    private int recursionDepth;
    /**
     * The opacity level of the circles used in the fractal elements.
     */
    private float circleOpacity;
    /**
     * The color used for the fractal elements.
     */
    private Color color;

    /**
     * Attaches an observer to the fractal subject.
     *
     * @param obs The observer to be added to the list of observers.
     */
    @Override
    public void attach(FractalObserver obs) {
        observers.add(obs);
    }

    /**
     * Detaches an observer from the fractal subject.
     *
     * @param obs The observer to be removed from the list of observers.
     */
    @Override
    public void detach(FractalObserver obs) {
        observers.remove(obs);
    }

    /**
     * Notifies all attached observers of a change.
     */
    @Override
    public void notifyAllObservers() {
        for (FractalObserver obs : observers) {
            obs.update();
        }
    }

    /**
     * Generates the list of fractal elements based on the current recursion depth.
     *
     * @return The list of fractal elements.
     */
    @Override
    public ArrayList<FractalElement> getData() {
        elements.clear();
        generateTriangle(new int[]{450, 250, 650}, new int[]{50, 450, 450}, recursionDepth);
        return elements;
    }

    /**
     * Sets user options for fractal generation and notifies all observers of the update.
     *
     * @param recursionDepth The depth of recursion for fractal generation.
     * @param circleOpacity  The opacity of the circles in the fractal.
     * @param color          The color of the fractal elements.
     */
    @Override
    public void setUserOptions(int recursionDepth, float circleOpacity, Color color) {
        this.recursionDepth = recursionDepth;
        this.circleOpacity = circleOpacity;
        this.color = color;
        notifyAllObservers();
    }

    /**
     * Recursively generates triangles and circles for the fractal, adding them to the elements list.
     *
     * @param xPoints The x-coordinates of the triangle's vertices.
     * @param yPoints The y-coordinates of the triangle's vertices.
     * @param depth   The current depth of recursion.
     */
    private void generateTriangle(int[] xPoints, int[] yPoints, int depth) {
        if (depth == 1) {
            elements.add(new Triangle(xPoints, yPoints, color));
            Circle circle = findCircle(xPoints[0], yPoints[0], xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
            elements.add(circle);
        } else {
            elements.add(new Triangle(xPoints, yPoints, color));


            if (depth > 1) {
                int x1 = (xPoints[0] + xPoints[1]) / 2;
                int y1 = (yPoints[0] + yPoints[1]) / 2;

                int x2 = (xPoints[1] + xPoints[2]) / 2;
                int y2 = (yPoints[1] + yPoints[2]) / 2;

                int x3 = (xPoints[2] + xPoints[0]) / 2;
                int y3 = (yPoints[2] + yPoints[0]) / 2;

                generateTriangle(new int[]{xPoints[0], x1, x3}, new int[]{yPoints[0], y1, y3}, depth - 1);
                generateTriangle(new int[]{x1, xPoints[1], x2}, new int[]{y1, yPoints[1], y2}, depth - 1);
                generateTriangle(new int[]{x3, x2, xPoints[2]}, new int[]{y3, y2, yPoints[2]}, depth - 1);

                Circle circle1 = findCircle(x1, y1, x2, y2, x3, y3);
                elements.add(circle1);

            }
        }
    }


    /**
     * Calculates the circle of a triangle given its vertices.
     *
     * @param x1 The x-coordinate of the first vertex.
     * @param y1 The y-coordinate of the first vertex.
     * @param x2 The x-coordinate of the second vertex.
     * @param y2 The y-coordinate of the second vertex.
     * @param x3 The x-coordinate of the third vertex.
     * @param y3 The y-coordinate of the third vertex.
     *
     * @return A circle representing the dimensions of the triangle.
     */
    private Circle findCircle(int x1, int y1, int x2, int y2, int x3, int y3) {
        double a = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double b = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double c = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        double s = (a + b + c) / 2;

        int Ix = (int) ((a * x1 + b * x2 + c * x3) / (a + b + c));
        int Iy = (int) ((a * y1 + b * y2 + c * y3) / (a + b + c));

        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        double r = 2 * area / (a + b + c);

        r = Math.min(r, Math.min(Math.min(a, b), c) / 2);

        float decimalOpacity = circleOpacity / 100.0f;
        int alphaValue = (int) (decimalOpacity * 255);
        alphaValue = Math.max(0, Math.min(255, alphaValue));
        Color circleColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaValue);

        return new Circle(Ix, Iy, (int) r, alphaValue, circleColor);
    }
}
