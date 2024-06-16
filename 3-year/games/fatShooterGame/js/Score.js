class Score {
  #score;

  constructor() {
    this.#score = 0;
  }

  /**
   * Pega a pontuação atual
   */
  get value() {
    return this.#score;
  }

  /**
   * Adiciona pontuação
   */
  add(value) {
    this.#score += value;
  }
}

export default Score;
