import Enemy from "./Enemy.js";

class Mamau extends Enemy {
  constructor(ctx, x, y, speed) {
    super(ctx, x, y, speed, 835, 34);
  }

  scorePoint() {
    return 5;
  }

  dealDamage() {
    return 5;
  }
}

export default Mamau;
