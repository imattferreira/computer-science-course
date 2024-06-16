const DIRECAO_ESQUERDA = 1;
const DIRECAO_DIREITA = 2;

function Heroi(context, teclado, animacao) {
  this.context = context;
  this.teclado = teclado;
  this.animacao = animacao;
  this.x = 0;
  this.y = 0;
  this.direcao = DIRECAO_DIREITA;
  this.img = new Image();

  this.img.src = "./img/heroiatirapeq.JPG";
}
Heroi.prototype = {
  frame: function () {
    if (this.teclado.pressionada(SETA_ESQUERDA) && this.x > 0) {
      this.x -= 10;
    } else if (
      this.teclado.pressionada(SETA_DIREITA) &&
      this.x < this.context.canvas.width - 20
    ) {
      this.direcao = DIRECAO_DIREITA;
      this.x += 10;
    }
  },

  draw: function () {
    this.context.drawImage(this.img, this.x, this.y, 20, 50);
  },

  atirar: function () {
    const tiro = new Ball(this.context);
    tiro.x = this.x + 10;
    tiro.y = this.y + 10;
    tiro.radius = 2;
    tiro.color = "red";
    if (this.teclado.pressionada(SETA_ESQUERDA)) {
      tiro.speedX = -20;
    } else {
      tiro.speedX = 20;
    }

    this.animacao.sprite(tiro);
  },
};
