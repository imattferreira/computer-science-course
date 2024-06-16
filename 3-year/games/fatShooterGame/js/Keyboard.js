class Keyboard {
  static x = 750 / 2;
  static y = window.innerHeight - 33;

  constructor(minX, maxX) {
    this.minX = minX;
    this.maxX = maxX;
  }

  /**
   * Move o ponteiro interno (x e y) para a esquerda, se caso já estiver no valor
   * mínimo, o ponteiro não é alterado.
   */
  toLeft() {
    Keyboard.x -= 20;

    if (Keyboard.x < this.minX) {
      Keyboard.x = this.minX;
    }
  }

  /**
   * Move o ponteiro interno (x e y) para a direita, se caso já estiver no valor
   * máximo, o ponteiro não é alterado.
   */
  toRight() {
    Keyboard.x += 20;

    if (Keyboard.x > this.maxX) {
      Keyboard.x = this.maxX;
    }
  }
}

export default Keyboard;
