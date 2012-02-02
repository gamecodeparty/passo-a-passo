import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

public class Jogo {

  class Sprite {
    Image imagem;
    int x;
    int y;
    int largura() { return imagem.getWidth(null); };
    int altura() { return imagem.getHeight(null); };
  }

  int largura = 600;
  int altura = 400;

  int framesPorSegundo = 40;
  boolean jogando;

  Graphics2D g;

  int velocidade = 8;

  Sprite alien;

  public static void main(String[] args) throws Exception {
    new Jogo().jogar();
  }

  void jogar() throws Exception {
    Tela tela = new Tela(largura, altura);
    tela.setVisible(true);
    g = tela.criarGraficos();

    inicializar();
    cicloPrincipal(tela);
  }

  void inicializar() throws Exception {
    alien = new Sprite();
    alien.imagem = ImageIO.read(Jogo.class.getResource("/alien.png"));
  }

  void cicloPrincipal(Tela tela) throws Exception {
    long marca = 0;

    jogando = true;
    while (jogando) {
      marca = System.currentTimeMillis() + (1000 / framesPorSegundo);

      interagir();
      atualizar();
      
      g.clearRect(0, 0, largura, altura);
      desenhar();
      tela.atualizar();

      if (marca > System.currentTimeMillis())
        Thread.sleep(marca - System.currentTimeMillis());
      marca = System.currentTimeMillis() + (1000 / framesPorSegundo);
    }

    tela.dispose();
  }

  void interagir() {
    if (Teclas.get(KeyEvent.VK_UP))
      alien.y -= velocidade;
    if (Teclas.get(KeyEvent.VK_DOWN))
      alien.y += velocidade;
    if (Teclas.get(KeyEvent.VK_LEFT))
      alien.x -= velocidade;
    if (Teclas.get(KeyEvent.VK_RIGHT))
      alien.x += velocidade;

    if (Teclas.get(KeyEvent.VK_ESCAPE))
      jogando = false;
  }

  void atualizar() {
    if (alien.x < 0) alien.x = 0;
    if (alien.y < 0) alien.y = 0;
    if (alien.x + alien.largura() > largura) alien.x = largura - alien.largura();
    if (alien.y + alien.altura() > altura) alien.y = altura - alien.altura();
  }

  void desenhar() {
    desenharSprite(alien);
  }
  
  void desenharSprite(Sprite sprite) {
    g.drawImage(sprite.imagem, sprite.x, sprite.y, null);
  }
}
