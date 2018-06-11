package base;

import java.util.Date;

public class Student {

    private Long id;

    private String name;

    private Boolean sex;

    private Date birthday;

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getSex() {
        return sex;
    }

    public Student setSex(Boolean sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Student setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
}
