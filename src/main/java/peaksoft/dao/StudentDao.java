package peaksoft.dao;

import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public interface StudentDao {
    // create table
    void crateTable();

    // save students
    void saveStudents(Student student);

    // find by students id
    Student findByStudentId(Long studentId);

    // find all
    List<Student> findByAllStudents();

    // update students
    void updateStudent(Long id, Student newStudent);

    // delete
    void deleteByStudentId(Long studentId);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();


    void deleteAllStudents(); //ddl
}
