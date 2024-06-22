import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI application for drawing sierpinski fractals.
 * Allows the user to choose recursion depth, opacity, and color of the drawing.
 */
public class FractalGui extends JFrame {
    /**
     * The subject representing the fractal data observed by the GUI to update the fractal.
     */
    private FractalSubject subject;


    /**
     * Main method to run the FractalGui Application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FractalGui gui = new FractalGui();
    }

    /**
     * Creates a new FractalGui window.
     * Initializes the GUI components and the listeners.
     */
    public FractalGui() {
        subject = new FractalGenerator();

        setTitle("FractalGui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setResizable(false);

        JPanel mainPanel = new JPanel(null);
        getContentPane().add(mainPanel);

        JLabel depthLabel = new JLabel("Recursion depth:");
        depthLabel.setBounds(70, 25, 100, 30);
        mainPanel.add(depthLabel);

        JSlider depthSlider = new JSlider(JSlider.HORIZONTAL,1,8,5);
        depthSlider.setBounds(25, 50, 300, 50);
        depthSlider.setMajorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);
        mainPanel.add(depthSlider);

        JLabel opacityLabel = new JLabel("Opacity");
        opacityLabel.setBounds(70, 115, 300, 50);
        mainPanel.add(opacityLabel);

        JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
        opacitySlider.setBounds(25, 150, 300, 50);
        opacitySlider.setMajorTickSpacing(50);
        opacitySlider.setPaintTicks(true);
        opacitySlider.setPaintLabels(true);
        mainPanel.add(opacitySlider);

        JLabel colorLabel = new JLabel("    ");
        colorLabel.setOpaque(true);
        colorLabel.setBounds(50, 225, 75, 25);
        mainPanel.add(colorLabel);

        JButton colorButton = new JButton("Choose Color");
        colorButton.setBounds(50, 250, 150, 25);
        colorButton.addActionListener(new ActionListener() {
            /**
             * Creates an action event when the "Choose Color" button is clicked.
             * Opens a color chooser dialog to select a color and updates the user options.
             *
             * @param e the event to be processed when "Choose Color" button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                if (color != null) {
                    colorLabel.setBackground(color);
                }
            }
        });
        mainPanel.add(colorButton);

        JButton drawButton = new JButton("Draw Fractal");
        drawButton.setBounds(50, 300, 150, 25);

        drawButton.addActionListener(new ActionListener() {
            /**
             * Creates an action event when the "Draw Fractal" button is clicked.
             * Gets user input values, and updates the fractal drawing.
             *
             * @param e the event to be processed when "Draw Fractal" button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int depth = depthSlider.getValue();
                int opacity = opacitySlider.getValue();
                Color color = colorLabel.getBackground();
                subject.setUserOptions(depth, opacity, color);
                

                JFrame fractalFrame = new JFrame("Fractal Drawing");
                fractalFrame.setSize(800,800);
                fractalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                if (fractalFrame != null) {
                    fractalFrame.dispose();
                }

                FractalDrawing fractalDrawing = new FractalDrawing(subject);
                fractalFrame.add(fractalDrawing, BorderLayout.CENTER);
                fractalFrame.setVisible(true);

            }
        });
        mainPanel.add(drawButton);

        setVisible(true);
    }
}
