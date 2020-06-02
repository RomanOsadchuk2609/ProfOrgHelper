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
@Table(name = "MathForm")
public class MathForm extends Model {

    @Column(name = "uri")
    private String uri;

    public MathForm() {
        super();
    }

    public MathForm(String uri) {
        super();
        this.uri = uri;
    }

    public static List<MathForm> findAll() {
        return new Select()
                .from(MathForm.class)
                .orderBy("id")
                .execute();
    }

    public static MathForm findFirst() {
        return new Select()
                .from(MathForm.class)
                .orderBy("id")
                .executeSingle();
    }
}
