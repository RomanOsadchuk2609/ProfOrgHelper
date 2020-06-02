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
@Table(name = "MathHelp")
public class MathHelp extends Model {

    @Column(name = "student", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private Student student;

    @Column(name = "year")
    private String year;

    @Column(name = "monthOfYear")
    private String month;

    @Column(name = "status")
    private int status;

    public MathHelp() {
        super();
    }

    public MathHelp(Student student, String year, String month, int status) {
        super();
        this.student = student;
        this.year = year;
        this.month = month;
        this.status = status;
    }

    public static List<MathHelp> findAll() {
        return new Select()
                .from(MathHelp.class)
                .orderBy("id")
                .execute();
    }

    public static MathHelp findByStudentAndYearAndMonth(Student student, String year, String month) {
        List<MathHelp> mathHelps = new Select()
                .from(MathHelp.class)
                .where("student = " + student.getId())
                .and("year = " + year)
                .execute();
        return findByMonth(mathHelps, month);
    }

    private static MathHelp findByMonth(List<MathHelp> list, String month) {
        if (list != null || !list.isEmpty()) {
            for (MathHelp mathHelp : list) {
                if (month.equals(mathHelp.getMonth())) {
                    return mathHelp;
                }
            }
        }
        return null;
    }
}
