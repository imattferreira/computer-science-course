class Ball {
  constructor(ctx) {
    this.ctx = ctx;
    this.x = 0;
    this.y = 0;
    this.speedX = 0;
    this.speedY = 0;
    this.color = "black";
    this.radius = 10;
  }

  draw() {
    this.ctx.save();
    this.ctx.fillStyle = this.color;

    this.ctx.beginPath();
    this.ctx.arc(this.x, this.y, this.radius, 0.2 * Math.PI, false);
    this.ctx.fill();
    this.ctx.restore();
  }

  frame() {
    this.x += this.speedX;
    this.y += this.speedY;
  }
}
