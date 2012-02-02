public class Teclas {

  private static boolean teclas[] = new boolean[255];

  static boolean get(int tecla) {
    if (tecla >= teclas.length)
      return false;
    return teclas[tecla];
  }

  static void set(int tecla, boolean valor) {
    if (tecla < teclas.length)
      teclas[tecla] = valor;
  }
}
