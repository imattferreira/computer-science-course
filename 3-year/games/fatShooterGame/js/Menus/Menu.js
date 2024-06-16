class Menu {
  #el;

  /**
   * Mostra o menu em tela
   */
  open() {
    this.#el.style.display = "flex";
  }

  /**
   * Esconde o menu em tela
   */
  close() {
    this.#el.style.display = "none";
  }
}

export default Menu;
