package domain.interfaces;

public interface InterfaceRepositorio<T> {
  public void popular(int qdd);
  public void incluir(int index);
  public void incluir(T novo);
  public void alterar();
   public T consultarPosicao(int posicao);
  public void excluir(int id);
}
