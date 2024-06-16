import Enemy from "../Enemies/Enemy.js";
import HealthKit from "./HealthKit.js";

class HealthKits {
  #healthKits;
  #ctx;

  constructor(ctx) {
    this.#ctx = ctx;
    this.#healthKits = [];
  }

  /**
   * Retorna os health-kits que estão em tela
   */
  get items() {
    return this.#healthKits;
  }

  /**
   * Retorna um health-kit específico a partir de seu índice
   */
  get(index) {
    return this.#healthKits[index];
  }

  /**
   * Atualiza a posição de todos os health-kits
   */
  update() {
    for (const healthKit of this.#healthKits) {
      healthKit.update();
    }
  }

  /**
   * Remove um health-kit especifico a partir de seu índice
   */
  remove(index) {
    this.#healthKits.splice(index, 1);
  }

  /**
   * Cria um novo health-kit, calculando a sua posição e a sua velocidade
   */
  create() {
    const x = Math.random() * (750 - Enemy.width);
    const y = -Enemy.height;
    const speed = Math.random() * 2.6;

    this.#healthKits.push(new HealthKit(this.#ctx, x, y, speed));
  }
}

export default HealthKits;
