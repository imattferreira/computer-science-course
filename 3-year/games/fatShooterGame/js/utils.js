/**
 * Gera um número randômico que esteja dentre o valor mínimo e o valor máximo
 */
export function randomNumber(min, max) {
  return Math.floor(Math.random() * (max + 1 - min) + min);
}
