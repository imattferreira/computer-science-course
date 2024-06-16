package repositories.in_memory;

import entities.Student;
import library.Parser;
import repositories.interfaces.IStudentsRepository;

import java.util.ArrayList;

public class InMemoryStudentsRepository implements IStudentsRepository {
    private ArrayList<Student> repository;
    private final Repository manipulator;
    private static InMemoryStudentsRepository _instance;

    public InMemoryStudentsRepository() {

        this.loadData();
        this.manipulator = new Repository("students.csv");
    }

    public static InMemoryStudentsRepository getInstance() {
        if (_instance == null) {
            _instance = new InMemoryStudentsRepository();
        }

        return _instance;
    }

    private void loadData() {
      this.repository = new ArrayList<Student>();

      ArrayList<String[]> data = this.manipulator.getContent();

      for (String[] line : data) {
        this.repository.add(this.mapper(line));
      }
    }

    private Student mapper(String[] fields) {
        int ra = Parser.parseInt(fields[0]);
        String name = Parser.parseStr(fields[1]);

        return new Student(ra, name);
    }

    @Override
    public void create(Student student) {
        this.repository.add(student);
    }

    @Override
    public ArrayList<Student> findAll() {
        return this.repository;
    }

    @Override
    public Student findByRa(int ra) {
      Student result = null;

      for (Student student : this.repository) {
        if (student.getRa() == ra) {
          result = student;
        }
      }

      return result;
    }
}
