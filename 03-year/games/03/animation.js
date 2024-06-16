class Animation {
  constructor(ctx) {
    this.ctx = ctx;
    this.sprites = [];
    this.enabled = false;
  }

  start() {
    this.enabled = true;
    this.#nextFrame();
  }

  stop() {
    this.enabled = false;
  }

  sprite(spr) {
    this.sprites.push(spr);
  }

  #nextFrame() {
    if (!this.enabled) {
      return;
    }

    this.#cleanup();

    for (const spr of this.sprites) {
      spr.frame();
    }

    for (const spr of this.sprites) {
      spr.draw();
    }

    requestAnimationFrame(() => {
      this.#nextFrame();
    });
  }

  #cleanup() {
    this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
  }
}
