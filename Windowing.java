import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Windowing extends JFrame {
    private int x, y; // Koordinat titik

    public Windowing() {
        setTitle("Windowing");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menambahkan listener untuk mendapatkan input dari keyboard
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        y -= 10; // Menggerakkan titik ke atas
                        break;
                    case KeyEvent.VK_DOWN:
                        y += 10; // Menggerakkan titik ke bawah
                        break;
                    case KeyEvent.VK_LEFT:
                        x -= 10; // Menggerakkan titik ke kiri
                        break;
                    case KeyEvent.VK_RIGHT:
                        x += 10; // Menggerakkan titik ke kanan
                        break;
                }

                repaint(); // Memperbarui tampilan setelah perubahan koordinat
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10); // Menggambar titik pada koordinat yang ditentukan
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Windowing());
    }
}