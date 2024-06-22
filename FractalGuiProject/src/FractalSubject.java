import java.util.ArrayList;
import java.awt.Color;

/**
 * The FractalSubject interface defines methods for managing observers and provides fractal data.
 * Allows observers to attach, detach, notified of updates, and access current fractal data.
 */
public interface FractalSubject {
    /**
     * Attaches an observer to the subject.
     * Observer will be notified of changes to the fractal data.
     * @param obs The observer to be added.
     */
    void attach(FractalObserver obs);

    /**
     * Detaches an observer from the subject.
     * Observer will no longer be notified of the fractal data.
     * @param obs The observer to be removed.
     */
    void detach(FractalObserver obs);

    /**
     * Notifies all attached observers of a change in the fractal data.
     */
    void notifyAllObservers();

    /**
     * Retrieves the fractal data into an array list.
     *
     * @return An ArrayList of FractalElement objects representing current fractal data.
     */
    ArrayList<FractalElement> getData();

    /**
     * Sets the user options for generating fractal data (recursion depth, opacity, color).
     *
     * @param recursionDepth The depth of recursion to be used for generating fractals.
     * @param opacity        The opacity of the fractal elements.
     * @param color          The color to be used for the fractal elements.
     */
    void setUserOptions(int recursionDepth, float opacity, Color color);
}
