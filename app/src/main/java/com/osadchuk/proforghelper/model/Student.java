package com.osadchuk.proforghelper.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "Student")
public class Student extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "educationForm")
    private String educationForm;

    @Column(name = "memberOfProfComm")
    private boolean memberOfProfComm;

    @Column(name = "fromOtherCity")
    private boolean fromOtherCity;

    @Column(name = "married")
    private boolean married;

    @Column(name = "children")
    private String children;

    @Column(name = "familyIssues")
    private String familyIssues;

    public Student() {
        super();
    }

    public Student(String name,
                   String educationForm,
                   boolean memberOfProfComm,
                   boolean fromOtherCity,
                   boolean married,
                   String children,
                   String familyIssues) {
        super();
        this.name = name;
        this.educationForm = educationForm;
        this.memberOfProfComm = memberOfProfComm;
        this.fromOtherCity = fromOtherCity;
        this.married = married;
        this.children = children;
        this.familyIssues = familyIssues;
    }

    public static List<Student> findAll() {
        return new Select()
                .from(Student.class)
                .orderBy("name")
                .execute();
    }

    public static Student findById(long id) {
        return new Select()
                .from(Student.class)
                .where("id = " + id)
                .executeSingle();
    }
}
