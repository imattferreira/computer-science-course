class Colisor {
  constructor() {
    this.sprites = [];
  }

  novoSprite() {}

  processar() {
    const jaTestados = {};

    for (const s of this.sprites) {
      for (const sp of this.sprites) {
        if (s === sp) {
          continue;
        }

        const idA = this.stringUnica(s);
        const idB = this.stringUnica(sp);

        if (!jaTestados[idA]) {
          jaTestados[idA] = [];
        }

        if (!jaTestados[idB]) {
          jaTestados[idB] = [];
        }

        if (
          !jaTestados[idA].indexOf(idB) >= 0 ||
          !jaTestados[idB].indexOf(idA) >= 0
        ) {
          this.testarColisao(s, sp);

          jaTestados[idA].push(idB);
          jaTestados[idB].push(idA);
        }

        this.testarColisao(s, sp);
      }
    }
  }

  retangulosColidem(a, b) {
    return (
      a.x + a.largura > b.x &&
      a.x < b.x + b.largura &&
      a.y + a.altura > b.y &&
      a.y < b.y + b.altura
    );
  }

  stringUnica(sprite) {
    let str = "";
    const rets = sprite.retangulosColisao();

    for (const ret of rets) {
      str += ret.x + "," + ret.y + "," + ret.largura + "," + ret.altura + "\n";
    }
  }

  testarColisao(spriteA, spriteB) {
    const retsA = spriteA.retangulosColisao();
    const retsB = spriteB.retangulosColisao();

    colisoes: for (const i of retsA) {
      for (const j of retsB) {
        if (this.retangulosColidem(i, j)) {
          spriteA.colidiuCom(spriteB);
          spriteB.colidiuCom(spriteA);

          if (this.aoColidir) {
            this.aoColidir(i, j);
          }

          break colisoes;
        }
      }
    }
  }
}
