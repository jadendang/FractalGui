import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 * The FractalDrawing is a JPanel that observes changes in a FractalSubject.
 * Renders the fractal element.
 * Implements the FractalObserver interface, allowing it to be notified for updates to the fractal's data.
 */
public class FractalDrawing extends JPanel implements FractalObserver {
    /**
     * The subject representing the fractal data observed by the GUI to update the fractal.
     */
    private FractalSubject subject;

    /**
     * Constructs a new FractalDrawing panel that observes a specified FractalSubject.
     *
     * @param subject the FractalSubject to be observed.
     */
    public FractalDrawing(FractalSubject subject) {
        this.subject = subject;
        subject.attach(this);
        setBackground(Color.BLACK);
    }

    /**
     * Paints the fractal elements onto the panel.
     * This method is called when the drawing is rendered.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight()); // Clear the panel with black

        ArrayList<FractalElement> elements = subject.getData();
        for (FractalElement element : elements) {
            element.draw(g); // Each element knows how to draw itself
        }
    }

    /**
     * Called when the fractal data is updated, repaints the panel.
     */
    @Override
    public void update() {
        repaint();
    }
}
