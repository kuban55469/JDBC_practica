package peaksoft.dao;

import peaksoft.config.DataBaseConnection;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public class StudentDaoImpl implements StudentDao {
    private Connection connection;

    public StudentDaoImpl() {
        this.connection = DataBaseConnection.getConnection();
    }

    @Override
    public void crateTable() {
        String query = """
                create table if not exists students(
                id serial primary key,
                name varchar(50) not null,
                age smallint not null
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveStudents(Student student) {
        String sqlQuery = """
                insert into students(name, age)
                values (?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setByte(2, student.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findByStudentId(Long studentId) {
        String query = """
                select * from students where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student = new Student();
            if (!resultSet.next()) {
                System.out.println("does not exists.");
            }
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString(2));
            student.setAge(resultSet.getByte(3));
            resultSet.close();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findByAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        String query = """
                select * from students;
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));

                allStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStudents;
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        String query = """
                update students 
                set name = ?,
                age = ?
                where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, id);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Successfully update.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByStudentId(Long studentId) {
        String sql = """
                delete from students where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        try {
            if (ascOrDesc == null)
                throw new Exception("Is null");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        List<Student> students = new ArrayList<>();
        String asc = """
                select * from students order by age asc ;
                """;
        String desc = """
                select * from students order by age desc ;
                """;
        if (ascOrDesc != null) {
            switch (ascOrDesc) {
                case "asc" -> {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(asc)) {
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            String name = resultSet.getString("name");
                            byte age = resultSet.getByte("age");
                            Student student = new Student(name, age);
                            students.add(student);
                        }
                        resultSet.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "desc" -> {
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(desc);
                         ResultSet resultSet = preparedStatement1.executeQuery()) {
                        while (resultSet.next()) {
                            String name = resultSet.getString("name");
                            byte age = resultSet.getByte("age");
                            Student student = new Student(name, age);
                            students.add(student);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return students;
    }

    @Override
    public boolean checkByAge() {
        for (Student student : getAllStudentsSortByAge("asc")) {
            if (student.getAge() >= 18) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deleteAllStudents() {
        String sql = """
                truncate table students;
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            rs.close();
            System.out.println("Students deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
