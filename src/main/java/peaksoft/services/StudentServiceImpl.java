package peaksoft.services;

import peaksoft.dao.StudentDao;
import peaksoft.dao.StudentDaoImpl;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public String crateTable() {
        studentDao.crateTable();
        return "Successfully created.";
    }

    @Override
    public String saveStudents(Student student) {
        studentDao.saveStudents(student);
        return "Successfully saved.";
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentDao.findByStudentId(studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findByAllStudents();
    }

    @Override
    public String updateStudent(Long id, Student newStudent) {
        studentDao.updateStudent(id,newStudent);
        return "Successfully update.";
    }

    @Override
    public String deleteByStudentId(Long studentId) {
        studentDao.deleteByStudentId(studentId);
        return "Successfully deleted.";
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        return studentDao.getAllStudentsSortByAge(ascOrDesc);
    }

    @Override
    public boolean checkByAge() {
        return studentDao.checkByAge();
    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }
}