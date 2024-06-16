import Keyboard from "./Keyboard.js";

const img = new Image();
img.src = "/imgs/sprites.png";

class Player {
  static height = 32;
  static width = 32;

  constructor(ctx) {
    this.ctx = ctx;
    this.x = Keyboard.x;
    this.y = Keyboard.y;

    this.height = Player.height;
    this.width = Player.width;
  }

  /**
   * Atualiza posição do jogador
   */
  draw() {
    this.ctx.beginPath();
    this.ctx.drawImage(
      img,
      25,
      29,
      44,
      50,
      Keyboard.x - Player.width,
      Keyboard.y - Player.height,
      48,
      48
    );
  }

  update() {
    this.draw();
  }
}

export default Player;
