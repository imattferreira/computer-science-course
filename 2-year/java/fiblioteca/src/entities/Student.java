package entities;

public class Student {
    private int ra;
    private String name;

    public Student(int ra, String name) {
        this.setRa(ra);
        this.setName(name);
    }

    public int getRa() {
        return this.ra;
    }

    private void setRa(int ra) {
        this.ra = ra;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void log() {
      System.out.println("=======================");
      System.out.println("RA - " + this.getRa());
      System.out.println("Nome - " + this.getName());
      System.out.println("=======================");
    }
}
