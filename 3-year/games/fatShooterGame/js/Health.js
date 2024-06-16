class Health {
  #health;

  constructor() {
    this.#health = 100;
  }

  /**
   * Retorna a vida atual
   */
  get value() {
    return this.#health;
  }

  /**
   * Diminui a vida atual pelo peso de hit do inimigo
   */
  hit(enemyHit) {
    if(enemyHit < this.#health) {
      this.#health -= enemyHit;
    } else {
      this.#health -= 1;
    }
  }

  /**
   * Acrescenta mais vida
   */
  bump() {
    this.#health += 20;
  }

  /**
   * Verifica se a vida ficou zerada
   */
  isDead() {
    return this.#health === 0;
  }
}

export default Health;
