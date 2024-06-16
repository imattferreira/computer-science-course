import Player from "./Player.js";
import Keyboard from "./Keyboard.js";
import Sound from "./Sound.js";
import Hud from "./Hud.js";
import Score from "./Score.js";
import Level from "./Level.js";
import Health from "./Health.js";
import Bullets from "./Bullets/Bullets.js";
import HealthKits from "./HealthKits/HealthKits.js";
import Enemies from "./Enemies/Enemies.js";
// import Parallax from "https://cdn.jsdelivr.net/npm/parallax.js@v5.5.0/dist/parallax.min.js";

const sound = new Sound();

window.onerror = () => {
  return true;
};

const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");

canvas.width = 750;
canvas.height = window.innerHeight;

ctx.font = "1em Arial";

const keyboard = new Keyboard(0 + Player.width, canvas.width - Player.width);

document.addEventListener("keydown", (e) => {
  if (e.code === "ArrowRight" || e.code === "KeyD") {
    keyboard.toRight();
  }

  if (e.code === "ArrowLeft" || e.code === "KeyA") {
    keyboard.toLeft();
  }
});

function createEnemyFactory(enemies) {
  let intervalId;

  return (interval) => {
    clearInterval(intervalId);
    intervalId = setInterval(() => {
      enemies.create();
    }, interval);
  };
}

function startGame() {
  const score = new Score();
  const level = new Level();
  const health = new Health();
  const enemies = new Enemies(ctx);
  const healthKits = new HealthKits(ctx);
  const bullets = new Bullets(ctx);
  const hud = new Hud(ctx, canvas.width - 100);
  const player = new Player(ctx);
  const createEnemy = createEnemyFactory(enemies);

  sound.startLoop();

  setInterval(() => {
    healthKits.create();
  }, 15000);

  setInterval(() => {
    bullets.create();
  }, 200);

  function animate() {
    requestAnimationFrame(animate);

    hud.update({
      health: health.value,
      level: level.getLevel(),
      score: score.value,
    });
    player.update();
    bullets.update();
    healthKits.update();
    enemies.update();

    if (score.value === level.getCeilScore()) {
      level.toNextLevel();
      createEnemy(level.getEnemiesIntervalInMs());
    }

    if (level.isWin()) {
      alert("You WIN!\n Your score was " + score.value);
      return startGame();
    }

    for (let k = 0; k < enemies.items.length; k++) {
      if (enemies.get(k).y > window.innerHeight) {
        health.hit(enemies.get(k).dealDamage());
        enemies.remove(k);

        if (health.isDead()) {
          sound.gameOver();
          alert("You DIED!\nYour score was " + score.value);
          return startGame();
        }
      }
    }

    for (let j = enemies.items.length - 1; j >= 0; j--) {
      for (let l = bullets.items.length - 1; l >= 0; l--) {
        if (bullets.get(l).collide(enemies.get(j))) {
          sound.kill();
          score.add(enemies.items[j].dealDamage());
          enemies.remove(j);
          bullets.remove(l);
        }
      }
    }

    for (let i = healthKits.items.length - 1; i >= 0; i--) {
      for (let j = bullets.items.length - 1; j >= 0; j--) {
        if (bullets.get(j).collide(healthKits.get(i))) {
          healthKits.remove(i);
          bullets.remove(j);
          sound.healthKit();
          health.bump();
        }
      }
    }
  }

  animate();
}

document.addEventListener(
  "click",
  () => {
    const menu = document.querySelector(".menu");

    menu.remove();
    startGame();
  },
  false
);
