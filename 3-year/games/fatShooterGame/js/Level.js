class Level {
  #level;

  constructor() {
    this.#level = 0;
  }

  /**
   * Pega o nível atual
   */
  getLevel() {
    return this.#level;
  }

  /**
   * Sobe o nível para o próximo
   */
  toNextLevel() {
    this.#level++;
  }

  /**
   * Verifica se o usuário ganhou o jogo
   */
  isWin() {
    return this.#level === 13;
  }

  /**
   * Calcula a pontuação máxima do nível atual
   */
  getCeilScore() {
    return this.#level * 50;
  }

  /**
   * Calcula quando um inimigo novo deve ser gerado
   */
  getEnemiesIntervalInMs() {
    return 1234 - this.#level * 100;
  }
}

export default Level;
