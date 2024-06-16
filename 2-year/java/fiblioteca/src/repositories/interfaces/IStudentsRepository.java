package repositories.interfaces;

import entities.Student;

import java.util.ArrayList;

public interface IStudentsRepository {
    public void create(Student student);
    public ArrayList<Student> findAll();
    public Student findByRa(int ra);
}
