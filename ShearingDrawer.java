import javax.swing.*;
import java.awt.*;

public class ShearingDrawer extends JFrame {
    private JTextField xField;
    private JTextField yField;
    private JTextField shearXField;
    private JTextField shearYField;
    private JButton drawButton;

    public ShearingDrawer() {
        setTitle("2D Shearing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel xLabel = new JLabel("X Coordinate:");
        xField = new JTextField();
        JLabel yLabel = new JLabel("Y Coordinate:");
        yField = new JTextField();
        JLabel shearXLabel = new JLabel("Shearing X:");
        shearXField = new JTextField();
        JLabel shearYLabel = new JLabel("Shearing Y:");
        shearYField = new JTextField();

        drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> drawShearing());

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(shearXLabel);
        add(shearXField);
        add(shearYLabel);
        add(shearYField);
        add(drawButton);

        setVisible(true);
    }

    private void drawShearing() {
        int x = Integer.parseInt(xField.getText());
        int y = Integer.parseInt(yField.getText());
        double shearX = Double.parseDouble(shearXField.getText());
        double shearY = Double.parseDouble(shearYField.getText());

        JFrame frame = new JFrame();
        frame.setTitle("2D Shearing - Output");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Shearing transformation
                g2d.shear(shearX, shearY);

                // Draw a point after shearing
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(x, y, x, y);
            }
        };

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShearingDrawer::new);
    }
}