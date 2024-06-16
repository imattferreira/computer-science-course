// [WIP]: DON'T TOUCH IN THIS SHIT!

import Game from "./Game.js";
import Keyboard from "./Keyboard.js";
import PauseMenu from "./Menus/PauseMenu.js";
import StartMenu from "./Menus/StartMenu.js";

const startMenu = new StartMenu();
const pauseMenu = new PauseMenu();
const keyboard = new Keyboard(0 + Player.width, canvas.width - Player.width);
const game = new Game();

startMenu.open();
pauseMenu.close();

const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");

canvas.width = 750;
canvas.height = window.innerHeight;

ctx.font = "1em Arial";

document.addEventListener("click", () => {
  if (game.isUnstarted()) {
    game.play();
    startMenu.close();
  }
});

document.addEventListener("keydown", (e) => {
  if (e.code === "ArrowRight" || e.code === "KeyD") {
    keyboard.toRight();
  }

  if (e.code === "ArrowLeft" || e.code === "KeyA") {
    keyboard.toLeft();
  }

  if (e.code === "Esc") {
    if (game.isPaused()) {
      game.play();
      pauseMenu.close();
    } else {
      game.pause();
      pauseMenu.open();
    }
  }
});

window.onerror = () => {
  return true;
};
