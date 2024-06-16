class Sound {
  #kill;
  #healthKit;
  #fire;
  #gameOver;
  #bg;

  constructor() {
    this.#healthKit = new Audio("audio/healthKit.mp3");
    this.#kill = new Audio("audio/kill.mp3");
    this.#fire = new Audio("audio/fire.mp3");
    this.#gameOver = new Audio("audio/gameOver.mp3");
    this.#bg = new Audio("audio/background.m4a");
  }

  /**
   * Inicia os sons intermitentes do jogo, como música de fundo e de tiro
   */
  startLoop() {
    this.#fire.play();
    this.#fire.loop = true;

    this.#bg.play();
    this.#bg.loop = true;

    this.#bg.volume = 0.1;
  }

  /**
   * Pausa músicas intermitentes
   */
  stopLoop() {
    this.#fire.pause();
    this.#bg.pause();
  }

  /**
   * Inicia som de morte
   */
  kill() {
    this.#kill.play();
  }

  /**
   * Inicia som de conseguir um bônus de vida
   */
  healthKit() {
    this.#healthKit.play();
  }

  /**
   * Inicia som de perder o jogo
   */
  gameOver() {
    this.#gameOver.play();
  }
}

export default Sound;
