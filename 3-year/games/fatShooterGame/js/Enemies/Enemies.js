import Enemy from "./Enemy.js";
import Cariri from "./Cariri.js";
import Xuliao from "./Xuliao.js";
import Mamau from "./Mamau.js";
import { randomNumber } from "../utils.js";

class Enemies {
  #enemies;
  #ctx;

  constructor(ctx) {
    this.#ctx = ctx;
    this.#enemies = [];
  }

  /**
   * Retorna os inimigos que estão em tela
   */
  get items() {
    return this.#enemies;
  }

  /**
   * Retorna um inimigo específico a partir de seu índice
   */
  get(index) {
    return this.#enemies[index];
  }

  /**
   * Remove um inimigo especifico a partir de seu índice
   */
  remove(index) {
    this.#enemies.splice(index, 1);
  }

  /**
   * Atualiza a posição de todos os inimigos
   */
  update() {
    for (const enemy of this.#enemies) {
      enemy.update();
    }
  }

  /**
   * Cria um novo inimigo, escolhendo o seu tipo de forma randômica e calculando
   * posição e velocidade
   */
  create() {
    const number = randomNumber(1, 3);
    let enemy;

    for (let _ = 0; _ < 4; _++) {
      const x = Math.random() * (750 - Enemy.width);
      const y = -Enemy.height;
      const speed = Math.random() * 2;

      switch (number) {
        case 1:
          enemy = new Cariri(this.#ctx, x, y, speed);
          break;
        case 2:
          enemy = new Mamau(this.#ctx, x, y, speed);
          break;
        case 3:
          enemy = new Xuliao(this.#ctx, x, y, speed);
          break;
        default:
          // do nothing
          break;
      }
    }
    this.#enemies.push(enemy);
  }
}

export default Enemies;
