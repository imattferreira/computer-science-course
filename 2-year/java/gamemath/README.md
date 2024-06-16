## GameMath

### Setup

1. Antes de mais nada, certifique-se de possuir um banco SQL Server ativo, estando consigo suas credenciais.
2. Em seu banco de dados, crie um schema, com o nome que desejar (recomendamos `gamemath` ou `game`)
3. Abra seu banco de dados em qualquer cliente de SQL, e rode as seguintes migrations, de acordo com o tipo de seu banco:

```sql
 CREATE TABLE records (
    id          INT           IDENTITY(1,1) PRIMARY KEY,
    username    VARCHAR(255),
    points      INT
);
```

4. Abra o arquivo `Repository.java`, e altere os atributos `url`, `username` e `password` de acordo com o seu banco de dados, exemplo:

```java
public class Repository {
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=game;encrypt=false;";
    private final String username = "root";
    private final String password = "docker";

    // restante do conteúdo da classe
}
```

5. Rode o projeto a partir da classe `Main.java`
6. Pronto, o projeto já estará funcionando corretamente!
