package com.osadchuk.proforghelper.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.Note;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_edit_note)
public class EditNoteActivity extends AppCompatActivity {

    @StringRes(R.string.note)
    String noteTitle;

    @ViewById
    EditText noteText;

    @ViewById
    Button btnSaveNote;

    @Extra
    long noteId;

    private Note note;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(noteTitle);
        }
        initNote();
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }

    @Click
    void btnSaveNote() {
        String text = noteText.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(this, R.string.input_note_text, Toast.LENGTH_SHORT).show();
        } else {
            note.setTest(noteText.getText().toString());
            note.save();
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        }
    }

    private void initNote() {
        if (noteId > 0) {
            note = Note.findById(noteId);
            noteText.setText(note.getTest());
        } else {
            note = new Note();
        }
    }
}
