import Menu from "./Menu.js";

class PauseMenu extends Menu {
  #el;

  constructor() {
    this.#el = document.querySelector(".pause-menu");
  }
}

export default PauseMenu;
