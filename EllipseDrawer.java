import java.awt.*;
import javax.swing.*;

public class EllipseDrawer extends JFrame {
    private JTextField xField;
    private JTextField yField;
    private JTextField widthField;
    private JTextField heightField;
    private JButton drawButton;

    public EllipseDrawer() {
        setTitle("Ellipse Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel xLabel = new JLabel("X Coordinate:");
        xField = new JTextField();
        JLabel yLabel = new JLabel("Y Coordinate:");
        yField = new JTextField();
        JLabel widthLabel = new JLabel("Width:");
        widthField = new JTextField();
        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField();

        drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> drawEllipse());

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(widthLabel);
        add(widthField);
        add(heightLabel);
        add(heightField);
        add(drawButton);

        setVisible(true);
    }

    private void drawEllipse() {
        int x = Integer.parseInt(xField.getText());
        int y = Integer.parseInt(yField.getText());
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());

        JFrame frame = new JFrame();
        frame.setTitle("Ellipse Drawer - Output");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawOval(x, y, width, height);
            }
        };

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EllipseDrawer::new);
    }
}