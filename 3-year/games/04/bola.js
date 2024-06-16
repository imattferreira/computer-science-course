class Bola {
  constructor(ctx) {
    this.ctx = ctx;
    this.x = 0;
    this.y = 0;
    this.velocidadeX = 0;
    this.velocidadeY = 0;
    this.cor = "black";
    this.raio = 10;
  }

  desenhar() {
    this.ctx.save();
    this.ctx.fillStyle = this.cor;
    this.ctx.beginPath();
    this.ctx.arc(this.x, this.y, this.raio, 0.2 * Math.PI, false);
    this.ctx.fill();
    this.ctx.restore();
  }

  atualizar() {
    if (this.x < this.radius || this.x > this.ctx.canvas.width - this.radius) {
      this.speedX *= -1;
    }

    if (this.y < this.radius || this.y > this.ctx.canvas.height - this.radius) {
      this.speedY *= -1;
    }

    this.x += this.speedX;
    this.y += this.speedY;
  }

  colidiuCom(sprite) {
    if (this.x < sprite.x) {
      this.velocidadeX = -Math.abs(this.velocidadeX);
    } else {
      this.velocidadeX = Math.abs(this.velocidadeX);
    }

    if (this.y < sprite.y) {
      this.velocidadeY = -Math.abs(this.velocidadeY);
    } else {
      this.velocidadeY = Math.abs(this.velocidadeY);
    }
  }

  retangulosColisao() {
    return {
      x: this.x - this.raio,
      y: this.y - this.raio,
      largura: this.raio * 2,
      altura: this.raio * 2,
    };
  }
}
