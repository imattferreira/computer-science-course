import Bullet from "./Bullet.js";

const img = new Image();

img.src = "/imgs/bullets/burguer.png";

class BurguerBullet extends Bullet {
  constructor(ctx, x, y) {
    super(ctx, x, y, 537, 34);
  }
}

export default BurguerBullet;
