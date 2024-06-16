// [WIP]: DON'T TOUCH IN THIS SHIT!

import Bullets from "./Bullets/Bullets.js";
import Enemies from "./Enemies/Enemies.js";
import Health from "./Health.js";
import HealthKits from "./HealthKits/HealthKits.js";
import Hud from "./Hud.js";
import Level from "./Level.js";
import Player from "./Player.js";
import Score from "./Score.js";
import Sound from "./Sound.js";

function createEnemyFactory(enemies) {
  let intervalId;

  return (interval) => {
    clearInterval(intervalId);
    intervalId = setInterval(() => {
      enemies.create();
    }, interval);
  };
}

class Game {
  #score;
  #level;
  #health;
  #enemies;
  #healthKits;
  #bullets;
  #hud;
  #player;
  #sound;
  #healthKitIntervalId;
  #bulletsIntervalId;
  #enemiesIntervalId;
  #status;

  constructor() {
    this.reset();
    this.#status = "unstarted";
  }

  /**
   * Inicia o jogo, iniciando também o loop para a criação dos health-kits
   * e balas
   */
  play() {
    this.#status = "play";
    this.#healthKitIntervalId = setInterval(() => {
      this.#healthKits.create();
    }, 15000);

    this.#bulletsIntervalId = setInterval(() => {
      this.#bullets.create();
    }, 200);

    this.animate();
  }

  /**
   * Loop de animação, fazendo todas as validações necessárias para atualizar
   * os personagens, as colisões, as estatísticas e confirmar se o usuário
   * ganhou ou perdeu.
   */
  animate() {
    requestAnimationFrame(animate);

    this.#hud.update({
      health: this.#health.value,
      level: this.#level.getLevel(),
      score: this.#score.value,
    });
    this.#player.update();
    this.#bullets.update();
    this.#healthKits.update();
    this.#enemies.update();

    if (this.#score.value === this.#level.getCeilScore()) {
      this.#level.toNextLevel();
      this.#createEnemy(this.#level.getEnemiesIntervalInMs());
    }

    if (this.#level.isWin()) {
      alert("You WIN!\n Your score was " + this.#score.value);
      return startGame();
    }

    for (let k = 0; k < this.#enemies.items.length; k++) {
      if (this.#enemies.get(k).y > window.innerHeight) {
        this.#enemies.remove(k);
        this.#health.hit(5);

        if (this.#health.isDead()) {
          this.#sound.gameOver();
          alert("You DIED!\nYour score was " + this.#score.value);
          return startGame();
        }
      }
    }

    for (let j = this.#enemies.items.length - 1; j >= 0; j--) {
      for (let l = this.#bullets.items.length - 1; l >= 0; l--) {
        if (this.#bullets.get(l).collide(this.#enemies.get(j))) {
          this.#enemies.remove(j);
          this.#bullets.remove(l);
          this.#sound.kill();
          this.#score.add(1);
        }
      }
    }

    for (let i = this.#healthKits.items.length - 1; i >= 0; i--) {
      for (let j = this.#bullets.items.length - 1; j >= 0; j--) {
        if (this.#bullets.get(j).collide(this.#healthKits.get(i))) {
          this.#healthKits.remove(i);
          this.#bullets.remove(j);
          this.#sound.healthKit();
          this.#health.bump();
        }
      }
    }
    this.animate();
  }

  /**
   * Pausa o loop de animação
   */
  pause() {
    this.#status = "paused";
    this.#sound.stopLoop();
    clearInterval(this.#healthKitIntervalId);
    clearInterval(this.#bulletsIntervalId);
    clearInterval(this.#enemiesIntervalId);
  }

  /**
   * Verifica se o jogo está pausado
   */
  isPaused() {
    return this.#status === "paused";
  }

  /**
   * Verifica se o jogo já foi iniciado
   */
  isUnstarted() {
    return this.#status === "unstarted";
  }

  /**
   * Volta ao inicio todos os estados internos do jogo, permitindo que o jogo
   * comesse novamente do zero
   */
  reset() {
    this.#status = "unstarted";
    menu.open();
    this.#score = new Score();
    this.#level = new Level();
    this.#health = new Health();
    this.#enemies = new Enemies();
    this.#healthKits = new HealthKits(ctx);
    this.#bullets = new Bullets(ctx);
    this.#hud = new Hud(ctx, canvas.width - 100);
    this.#player = new Player(ctx);
    this.#sound = new Sound();
  }

  /**
   * Cria loop de criação de inimigos, baseado no nível atual do usuário
   */
  #createEnemy(interval) {
    clearInterval(this.#enemiesIntervalId);
    this.#enemiesIntervalId = setInterval(() => {
      this.#enemies.create();
    }, interval);
  }
}

export default Game;
