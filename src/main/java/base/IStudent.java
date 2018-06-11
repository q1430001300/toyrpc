package base;

public interface IStudent {

    Student findById(Long student);

    void save(Student student);

    String findName(Long id);
}
