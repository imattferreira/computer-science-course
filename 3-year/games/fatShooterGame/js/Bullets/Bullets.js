import Keyboard from "../Keyboard.js";
import Player from "../Player.js";
import Bullet from "./Bullet.js";
import BurguerBullet from "./BurguerBullet.js";
import CoxinhaBullet from "./CoxinhaBullet.js";
import DonutBullet from "./DonutBullet.js";
import PizzaBullet from "./PizzaBullet.js";
import MilkshakeBullet from "./MilkshakeBullet.js";
import { randomNumber } from "../utils.js";

class Bullets {
  #bullets;
  #ctx;

  constructor(ctx) {
    this.#ctx = ctx;
    this.#bullets = [];
  }

  /**
   * Atualiza a posição de todas as balas, se caso alguma estiver saindo da
   * tela, ela é removida
   */
  update() {
    for (let i = 0; i < this.#bullets.length; i++) {
      this.#bullets[i].update();

      if (this.#bullets[i].y < 0) {
        this.#bullets.splice(i, 1);
      }
    }
  }

  /**
   * Retorna as balas que estão em tela
   */
  get items() {
    return this.#bullets;
  }

  /**
   * Retorna uma bala específica a partir de seu índice
   */
  get(index) {
    return this.#bullets[index];
  }

  /**
   * Remove uma bala específica a partir de seu índice
   */
  remove(index) {
    this.#bullets.splice(index, 1);
  }

  /**
   * Cria uma nova bala, escolhendo o seu tipo de forma randômica e calculando
   * posição (para ser a mesma do personagem) e velocidade
   */
  create() {
    const number = randomNumber(1, 5);
    const x = Keyboard.x - Bullet.width / 2;
    const y = Keyboard.y - Player.height;

    let bullet;

    switch (number) {
      case 1:
        bullet = new BurguerBullet(this.#ctx, x, y);
        break;
      case 2:
        bullet = new CoxinhaBullet(this.#ctx, x, y);
        break;
      case 3:
        bullet = new DonutBullet(this.#ctx, x, y);
        break;
      case 4:
        bullet = new MilkshakeBullet(this.#ctx, x, y);
        break;
      case 5:
        bullet = new PizzaBullet(this.#ctx, x, y);
        break;
      default:
        // do nothing
        break;
    }

    this.#bullets.push(bullet);
  }
}

export default Bullets;
