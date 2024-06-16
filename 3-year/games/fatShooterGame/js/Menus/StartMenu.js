import Menu from "./Menu.js";

class StartMenu extends Menu {
  #el;

  constructor() {
    this.#el = document.querySelector(".start-menu");
  }
}

export default StartMenu;
