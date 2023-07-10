import java.awt.*;
import javax.swing.*;

public class LineDrawer extends JFrame {
    private JTextField nameField;
    private JTextField x1Field;
    private JTextField y1Field;
    private JTextField x2Field;
    private JTextField y2Field;
    private JButton drawButton;

    public LineDrawer() {
        setTitle("Line Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Object Name:");
        nameField = new JTextField();
        JLabel x1Label = new JLabel("X1 Coordinate:");
        x1Field = new JTextField();
        JLabel y1Label = new JLabel("Y1 Coordinate:");
        y1Field = new JTextField();
        JLabel x2Label = new JLabel("X2 Coordinate:");
        x2Field = new JTextField();
        JLabel y2Label = new JLabel("Y2 Coordinate:");
        y2Field = new JTextField();

        drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> drawLine());

        add(nameLabel);
        add(nameField);
        add(x1Label);
        add(x1Field);
        add(y1Label);
        add(y1Field);
        add(x2Label);
        add(x2Field);
        add(y2Label);
        add(y2Field);
        add(drawButton);

        setVisible(true);
    }

    private void drawLine() {
        String name = nameField.getText();
        int x1 = Integer.parseInt(x1Field.getText());
        int y1 = Integer.parseInt(y1Field.getText());
        int x2 = Integer.parseInt(x2Field.getText());
        int y2 = Integer.parseInt(y2Field.getText());

        JFrame frame = new JFrame();
        frame.setTitle("Line Drawer - Output");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(x1, y1, x2, y2);
                g.drawString(name, (x1 + x2) / 2, (y1 + y2) / 2);
            }
        };

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LineDrawer::new);
    }
}