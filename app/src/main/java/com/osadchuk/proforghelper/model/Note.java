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
@Table(name = "Note")
public class Note extends Model {

    @Column(name = "test")
    private String test;

    public Note() {
        super();
    }

    public Note(String test) {
        super();
        this.test = test;
    }

    public static List<Note> findAll() {
        return new Select()
                .from(Note.class)
                .orderBy("id")
                .execute();
    }

    public static Note findById(long id) {
        return new Select()
                .from(Note.class)
                .where("id = " + id)
                .executeSingle();
    }
}
