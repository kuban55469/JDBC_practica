package peaksoft.services;

import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public interface StudentService {
    // create table
    String crateTable();

    // save students
    String saveStudents(Student student);

    // find by students id
    Student findByStudentId(Long studentId);

    //find all
    List<Student> findAllStudents();

    // update students
    String updateStudent(Long id, Student newStudent);

    // delete
    String deleteByStudentId(Long studentId);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void deleteAllStudents(); //ddl
}
