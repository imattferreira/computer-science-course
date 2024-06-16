const img = new Image();
img.src = "/imgs/sprites.png";

class Enemy {
  static width = 32;
  static height = 32;

  constructor(ctx, x, y, speed, spriteX, spriteY) {
    this.ctx = ctx;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.size = 32;
    this.spriteX = spriteX;
    this.spriteY = spriteY;

    this.width = Enemy.width;
    this.height = Enemy.height;
  }

  /**
   * Atualiza a posição do inimigo
   */
  draw() {
    this.ctx.beginPath();
    this.ctx.drawImage(
      img,
      this.spriteX,
      this.spriteY,
      this.width,
      this.height,
      this.x,
      this.y,
      this.width,
      this.height
    );
  }

  update() {
    this.y += this.speed;
    this.draw();
  }
}

export default Enemy;
