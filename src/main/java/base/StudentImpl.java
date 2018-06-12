package base;

import java.util.Date;

public class StudentImpl implements IStudent {
    @Override
    public Student findById(Long student) {
        return new Student().setId(student).setName("pmj").setSex(true).setBirthday(new Date());
    }

    @Override
    public void save(Student student) {
        throw new IllegalArgumentException();
    }

    @Override
    public String findName(Long id) {
        return id + "pmj";
    }
}
