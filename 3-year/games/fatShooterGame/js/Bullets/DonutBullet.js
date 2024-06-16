import Bullet from "./Bullet.js";

class DonutBullet extends Bullet {
  constructor(ctx, x, y) {
    super(ctx, x, y, 235, 34);
  }
}

export default DonutBullet;
