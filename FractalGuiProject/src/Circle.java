import java.awt.*;

/**
 * The Circle class represents a circle shape to be drawn.
 * Implements the FractalElement interface.
 */
public class Circle implements FractalElement {
    /**
     * The x-coordinate for positioning the circle component.
     */
    private int x;
    /**
     * The y-coordinate for positioning the circle component.
     */
    private int y;
    /**
     * The radius of the circle element.
     */
    private int radius;
    /**
     * The opacity of the circle element.
     */
    private float opacity;
    /**
     * The color of the circle element.
     */
    private Color color;

    /**
     * Constructs a new Circle with the specified parameters.
     *
     * @param x         The x-coordinate of the circle's center.
     * @param y         The y-coordinate of the circle's center.
     * @param radius    The radius of the circle.
     * @param opacity   The opacity of the circle.
     * @param color     The color of the circle.
     */
    public Circle(int x, int y, int radius, float opacity, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.opacity = opacity;
        this.color = color;
    }

    /**
     * Draws the circle on the specified Graphics context.
     *
     * @param g the Graphics context for the circle to be drawn.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}

