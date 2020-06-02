package com.osadchuk.proforghelper.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.ui.adapter.NoteListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_notes_list)
public class NotesListActivity extends AppCompatActivity {

    @Bean
    NoteListAdapter adapter;

    @StringRes(R.string.notes)
    String notesTitle;

    @ViewById
    FloatingActionButton fabAddNote;

    @ViewById
    ListView notesListView;

    private int oldFirsVisibleItem = 0;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(notesTitle);
        }
        notesListView.setAdapter(adapter);
        notesListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    oldFirsVisibleItem = view.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > oldFirsVisibleItem) {
                    fabAddNote.hide();
                } else {
                    fabAddNote.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.refresh();
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }

    @Click
    void fabAddNote() {
        EditNoteActivity_.intent(NotesListActivity.this).extra("noteId", 0).start();
    }

    public void refreshNotesList() {
        adapter.refresh();
    }
}
