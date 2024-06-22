import java.awt.*;

/**
 * The Triangle class represents a triangle shape to be drawn.
 * Implements FractalElement interface.
 */
public class Triangle implements FractalElement {
    /**
     * An array of x-coordinates for the vertices of the triangle.
     */
    private int[] xPoints;
    /**
     * An array of y-coordinates for the vertices of the triangle.
     */
    private int[] yPoints;
    /**
     * The color of the triangle.
     */
    private Color color;

    /**
     * Constructs a new Triangle with the specified vertex coordinates and color.
     *
     * @param xPoints   An array of x-coordinates for the triangle's vertices.
     * @param yPoints   An array of y-coordinates for the triangle's vertices.
     * @param color     The color for the triangle.
     */
    public Triangle(int[] xPoints, int[] yPoints, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    /**
     * Draws the triangle on the drawing panel.
     *
     * @param g The graphics context for the triangle to be drawn.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawPolygon(xPoints, yPoints, 3);

    }
}
