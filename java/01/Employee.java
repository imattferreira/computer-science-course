import java.util.Scanner;

class Employee {
  int code;
  String name;
  String fatherName;
  String motherName;
  String telephoneNumber;
  float baseSalary;
  float inssPercentage;
  float irrfPercentage;
  float familySalary;
  float transport;
  float medicalAssistance;
  int dependencies;

  // TODO transform into constructor
  public void input() {
    Scanner input = new Scanner(System.in);

    this.code = Integer.parseInt(Utils.printMessageAndReturnInput(input, "digite o código: "));
    this.name = Utils.printMessageAndReturnInput(input, "digite o nome: ");
    this.fatherName = Utils.printMessageAndReturnInput(input, "digite o nome do pai: ");
    this.motherName = Utils.printMessageAndReturnInput(input, "digite o nome da mãe: ");
    this.telephoneNumber = Utils.printMessageAndReturnInput(input, "digite o número de telefone: ");
    this.baseSalary = Float.parseFloat(Utils.printMessageAndReturnInput(input, "digite o salário-base: "));
    this.inssPercentage = Float.parseFloat(Utils.printMessageAndReturnInput(input, "digite a porcentagem de INSS: "));
    this.irrfPercentage = Float.parseFloat(Utils.printMessageAndReturnInput(input, "digite a porcentagem de IFFF: "));
    this.familySalary = Float
        .parseFloat(Utils.printMessageAndReturnInput(input, "digite o valor de salário família: "));
    this.transport = Float.parseFloat(Utils.printMessageAndReturnInput(input, "digite o valor de transporte: "));
    this.medicalAssistance = Float
        .parseFloat(Utils.printMessageAndReturnInput(input, "digite o valor de assistência médica: "));
    this.dependencies = Integer.parseInt(Utils.printMessageAndReturnInput(input, "digite o número de dependentes: "));
  }

  public float getTotalSalaryWithoutDiscounts() {
    return this.baseSalary + this.familySalary;
  }

  public float getTotalDiscounts() {
    float inss = Utils.getValueFromPercentage(this.inssPercentage, this.baseSalary);
    float irrf = Utils.getValueFromPercentage(this.irrfPercentage, this.baseSalary);

    return irrf + inss + this.transport + this.medicalAssistance;
  }

  public float getSalaryWithDiscounts() {
    return this.getTotalSalaryWithoutDiscounts() - this.getTotalDiscounts();
  }
}

class Utils {
  public static float getValueFromPercentage(float percentage, float total) {
    return (percentage * total) / 100;
  }

  public static String printMessageAndReturnInput(Scanner input, String message) {
    System.out.printf(message);
    return input.next();
  }
}

// TODO fix
class Main {
  public static void main(String[] args) {
    Employee employee = new Employee();

    employee.input();

    float salaryWithDiscounts = employee.getSalaryWithDiscounts();

    System.out.printf("código %d ", employee.code);
    System.out.printf("nome %s ", employee.name);
    System.out.printf("salário-base %.2f ", employee.baseSalary);
    System.out.printf("salário líquido %.2f ", salaryWithDiscounts);
  }
}
