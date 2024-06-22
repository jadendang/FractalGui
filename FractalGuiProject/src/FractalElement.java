import java.awt.Graphics;

/**
 * The FractalElement interface defines the method for drawing fractal elements.
 * Classes implementing FractalElement will be able to render themselves using a Graphics object.
 */
public interface FractalElement {
    /**
     * Draws the fractal element onto the provided drawing panel.
     * @param g The graphics context for the fractal element to be drawn.
     */
    void draw(Graphics g);
}
