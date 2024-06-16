package repositories.implementations;

import entities.Student;
import repositories.interfaces.IStudentsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentsRepository implements IStudentsRepository {
    private Repository repository;

    public StudentsRepository() {
        this.repository = Repository.getInstance();
    }

    @Override
    public void create(Student student) {
        try {
            PreparedStatement statement = this.repository.getConnection()
                .prepareStatement(
                    "INSERT INTO Students ("
                        + "ra,"
                        + "name "
                        + ") VALUES (?,?);"
                );

            statement.setInt(0, student.getRa());
            statement.setString(1, student.getName());

            statement.execute();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> result = new ArrayList<Student>();

        try {
            ResultSet lines = this.repository
                .getConnection()
                .prepareStatement("SELECT * FROM Students")
                .executeQuery();

            while (lines.next()) {
                int ra = lines.getInt("ra");
                String name = lines.getString("name");

                Student student = new Student(ra, name);

                result.add(student);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public Student findByRa(int ra) {
        try {
            ResultSet lines = this.repository
                .getConnection()
                .prepareStatement("SELECT * FROM Students WHERE ra = " + ra)
                .executeQuery();

            lines.next();

            String name = lines.getString("name");

            return new Student(ra, name);
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return null;
        }
    }
}
