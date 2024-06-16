import Enemy from "./Enemy.js";

class Cariri extends Enemy {
  constructor(ctx, x, y, speed) {
    super(ctx, x, y, speed, 732, 34);
  }

  scorePoint() {
    return 1;
  }

  dealDamage() {
    return 8;
  }
}

export default Cariri;
