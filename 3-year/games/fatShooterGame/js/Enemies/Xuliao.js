import Enemy from "./Enemy.js";

class Xuliao extends Enemy {
  constructor(ctx, x, y, speed) {
    super(ctx, x, y, speed, 933, 34);
  }

  scorePoint() {
    return 2;
  }

  dealDamage() {
    return 12;
  }
}

export default Xuliao;
