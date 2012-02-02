import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Tela extends Frame {

  private Canvas canvas;
  private BufferedImage imagem;

  public Tela(final int largura, final int altura) {
    imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

    setTitle("Main");
    setLayout(new BorderLayout());
    setResizable(false);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
      }

      public void windowClosed(WindowEvent e) {
        System.exit(0);
      }
    });

    canvas = new Canvas();
    canvas.setSize(largura, altura);
    canvas.setBackground(Color.WHITE);
    canvas.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        Teclas.set(e.getKeyCode(), true);
      }

      public void keyReleased(KeyEvent e) {
        Teclas.set(e.getKeyCode(), false);
      }
    });
    add(canvas);
    pack();
    
    canvas.requestFocus();

    // centralizando a tela
    Dimension area = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((area.width - largura) / 2, (area.height - altura) / 2);
  }

  public Graphics2D criarGraficos() {
    Graphics2D g = imagem.createGraphics();
    g.setColor(Color.BLACK);
    g.setBackground(Color.WHITE);
    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    return g;
  }

  public void atualizar() {
    canvas.getGraphics().drawImage(imagem, 0, 0, null);
  }
}
