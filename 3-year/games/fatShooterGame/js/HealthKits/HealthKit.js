const img = new Image();

img.src = "/imgs/sprites.png";

class HealthKit {
  static width = 32;
  static height = 32;

  constructor(ctx, x, y, speed) {
    this.ctx = ctx;
    this.x = x;
    this.y = y;
    this.speed = speed;

    this.width = HealthKit.width;
    this.height = HealthKit.height;
  }

  /**
   * Atualiza a posição do health-kit
   */
  draw() {
    this.ctx.beginPath();
    this.ctx.drawImage(
      img,
      135,
      34,
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

export default HealthKit;
