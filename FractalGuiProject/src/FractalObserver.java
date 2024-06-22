/**
 * The FractalObserver interface defines observers that need to be notified of changes.
 */
public interface FractalObserver {
    /**
     * Updates the observers when the state of the FractalSubject changes.
     */
    void update();
}
