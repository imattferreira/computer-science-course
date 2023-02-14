import java.util.Scanner;

class Main {
  // University info
  private int code;

  // Personal info
  private String name;
  private long cpf;
  private String rg;
  private String email;
  private boolean isMan;
  private float salary;
  private String telephoneNumber;
  private String phoneNumber;

  // Address info
  private String address;
  private int number;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;

  // TODO fix
  public static void main(String[] args) {
    Main student = new Main();

    student.input();
    student.output();
  }

  // TODO transform into constructor
  public void input() {
    Scanner input = new Scanner(System.in);

    // University info
    this.code = Integer.parseInt(Utils.printMessageAndReturnInput(input, "Digite o seu código de aluno: "));

    // Personal info
    this.name = Utils.printMessageAndReturnInput(input, "Digite o seu nome: ");
    this.cpf = Long.parseLong(Utils.printMessageAndReturnInput(input, "Digite o seu CPF: "));
    this.rg = Utils.printMessageAndReturnInput(input, "Digite o seu RG: ");
    this.email = Utils.printMessageAndReturnInput(input, "Digite o seu E-mail: ");
    this.isMan = Utils
        .parseBool(Utils.printMessageAndReturnInput(input, "Digite o seu sexo:\n 0 - feminino\n 1 - masculino\n"));
    this.salary = Float.parseFloat(Utils.printMessageAndReturnInput(input, "Digite o seu salário: "));
    this.telephoneNumber = Utils.printMessageAndReturnInput(input, "Digite o seu telefone: ");
    this.phoneNumber = Utils.printMessageAndReturnInput(input, "Digite o seu número de celular: ");

    // Address
    this.address = Utils.printMessageAndReturnInput(input,
        "Digite o seu endereço (somente nome da rua, avenida, etc): ");
    this.number = Integer.parseInt(Utils.printMessageAndReturnInput(input, "Digite o número de seu endereço: "));
    this.complement = Utils.printMessageAndReturnInput(input, "Digite o complemento de seu endereço: ");
    this.neighborhood = Utils.printMessageAndReturnInput(input, "Digite o nome de seu bairro: ");
    this.city = Utils.printMessageAndReturnInput(input, "Digite o nome da sua cidade: ");
    this.state = Utils.printMessageAndReturnInput(input, "Digite o nome de seu estado: ");

    input.close();
  }

  /**
   * %s -> string
   * %f -> float
   * %d -> integer
   * %b -> bool
   */
  public void output() {
    System.out.printf("\n\n\n\n");
    System.out.println("=============================");
    // University info
    System.out.printf("seu código de aluno é:\n", this.code);

    // Personal info
    System.out.printf("seu nome é %s\n", this.name);
    System.out.printf("seu CPF é %d\n", this.cpf);
    System.out.printf("seu RG é %s\n", this.rg);
    System.out.printf("seu email é %s\n", this.email);
    System.out.printf("seu sexo é %s\n", this.getSexName());
    System.out.printf("seu salário é %.2f\n", this.salary);
    System.out.printf("seu telefone é %s\n", this.telephoneNumber);
    System.out.printf("seu número de celular é %s\n", this.phoneNumber);

    // Address info
    System.out.printf("seu endereço é %s\n", this.address);
    System.out.printf("o número de endereço é %d\n", this.number);
    System.out.printf("o complemento de seu endereço é %s\n", this.complement);
    System.out.printf("seu bairro é %s\n", this.neighborhood);
    System.out.printf("sua cidade é %s\n", this.city);
    System.out.printf("seu estado é %s\n", this.state);
  }

  private String getSexName() {
    return this.isMan ? "masculino" : "feminino";
  }
}

class Utils {
  public static boolean parseBool(String value) {
    return value.equals("1");
  }

  public static String printMessageAndReturnInput(Scanner input, String message) {
    System.out.printf(message);
    return input.next();
  }
}