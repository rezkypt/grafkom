import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clipping extends JFrame {
    private int x1, y1, x2, y2; // Koordinat garis
    private int clipX1, clipY1, clipX2, clipY2; // Koordinat jendela clipping

    public Clipping() {
        setTitle("Clipping");
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
                        y1 -= 10; // Menggerakkan garis ke atas
                        y2 -= 10;
                        break;
                    case KeyEvent.VK_DOWN:
                        y1 += 10; // Menggerakkan garis ke bawah
                        y2 += 10;
                        break;
                    case KeyEvent.VK_LEFT:
                        x1 -= 10; // Menggerakkan garis ke kiri
                        x2 -= 10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        x1 += 10; // Menggerakkan garis ke kanan
                        x2 += 10;
                        break;
                }

                repaint(); // Memperbarui tampilan setelah perubahan koordinat
            }
        });

        // Mengatur koordinat jendela clipping
        clipX1 = 100;
        clipY1 = 100;
        clipX2 = 400;
        clipY2 = 400;

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Menggambar jendela clipping
        g.setColor(Color.BLUE);
        g.drawRect(clipX1, clipY1, clipX2 - clipX1, clipY2 - clipY1);

        // Menggambar garis
        g.setColor(Color.RED);
        clipLine(g, x1, y1, x2, y2, clipX1, clipY1, clipX2, clipY2);
    }

    // Metode untuk melakukan clipping garis
    private void clipLine(Graphics g, int x1, int y1, int x2, int y2, int clipX1, int clipY1, int clipX2, int clipY2) {
        int code1 = computeOutCode(x1, y1, clipX1, clipY1, clipX2, clipY2);
        int code2 = computeOutCode(x2, y2, clipX1, clipY1, clipX2, clipY2);

        while (true) {
            if ((code1 & code2) != 0) {
                return; // Garis berada di luar jendela clipping
            } else if ((code1 | code2) == 0) {
                // Garis berada di dalam jendela clipping
                g.drawLine(x1, y1, x2, y2);
                return;
            } else {
                int code = (code1 != 0) ? code1 : code2;

                int newX, newY;

                if ((code & 0x1) != 0) { // Kiri
                    newX = clipX1;
                    newY = y1 + (y2 - y1) * (clipX1 - x1) / (x2 - x1);
                } else if ((code & 0x2) != 0) { // Kanan
                    newX = clipX2;
                    newY = y1 + (y2 - y1) * (clipX2 - x1) / (x2 - x1);
                } else if ((code & 0x4) != 0) { // Bawah
                    newY = clipY1;
                    newX = x1 + (x2 - x1) * (clipY1 - y1) / (y2 - y1);
                } else { // Atas
                    newY = clipY2;
                    newX = x1 + (x2 - x1) * (clipY2 - y1) / (y2 - y1);
                }

                if (code == code1) {
                    x1 = newX;
                    y1 = newY;
                    code1 = computeOutCode(x1, y1, clipX1, clipY1, clipX2, clipY2);
                } else {
                    x2 = newX;
                    y2 = newY;
                    code2 = computeOutCode(x2, y2, clipX1, clipY1, clipX2, clipY2);
                }
            }
        }
    }

    // Metode untuk menghitung kode penentu untuk garis yang akan di-clip
    private int computeOutCode(int x, int y, int clipX1, int clipY1, int clipX2, int clipY2) {
        int code = 0;

        if (x < clipX1) {
            code |= 0x1; // Kode kiri
        } else if (x > clipX2) {
            code |= 0x2; // Kode kanan
        }

        if (y < clipY1) {
            code |= 0x4; // Kode bawah
        } else if (y > clipY2) {
            code |= 0x8; // Kode atas
        }

        return code;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Clipping());
    }
}