package com.osadchuk.proforghelper.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.osadchuk.proforghelper.model.Note;
import com.osadchuk.proforghelper.ui.itemView.NoteItemView;
import com.osadchuk.proforghelper.ui.itemView.NoteItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class NoteListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Note> noteList;

    @AfterInject
    void init() {
        noteList = Note.findAll();
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Note getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteItemView noteItemView;
        if (convertView == null) {
            noteItemView = NoteItemView_.build(context);
        } else {
            noteItemView = (NoteItemView) convertView;
        }
        noteItemView.bind(getItem(position));
        return noteItemView;
    }

    public void refresh() {
        noteList = Note.findAll();
        notifyDataSetChanged();
    }
}
