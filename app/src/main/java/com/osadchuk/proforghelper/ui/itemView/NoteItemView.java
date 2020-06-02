package com.osadchuk.proforghelper.ui.itemView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.Note;
import com.osadchuk.proforghelper.ui.activity.EditNoteActivity_;
import com.osadchuk.proforghelper.ui.activity.NotesListActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.note_item)
public class NoteItemView extends ConstraintLayout {

    @ViewById
    TextView noteTitle;

    @ViewById
    ImageButton btnDeleteNote;

    private Note note;

    private Context context;

    public NoteItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(Note note) {
        this.note = note;
        String text = this.note.getTest();
        if (text.contains("\n")) {
            text = text.substring(0, text.indexOf('\n'));
        }
        if (text.length() >= 40) {
            text = note.getTest().substring(0, 37).trim() + "...";
        }
        noteTitle.setText(text);
    }

    @Click
    void btnDeleteNote() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    deleteNote();
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Вы действительно хотите удалить заметку \"" + noteTitle.getText() + "\"?")
                .setPositiveButton("Да", dialogClickListener)
                .setNegativeButton("Нет", dialogClickListener)
                .show();
    }

    @Click
    void noteTitle() {
        EditNoteActivity_.intent(context).extra("noteId", note.getId()).start();
    }

    private void deleteNote() {
        if (note != null) {
            note.delete();
            note = null;
            if (context instanceof NotesListActivity) {
                ((NotesListActivity) context).refreshNotesList();
            }
        }
    }
}
