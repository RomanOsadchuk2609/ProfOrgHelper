package com.osadchuk.proforghelper.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.osadchuk.proforghelper.model.Student;
import com.osadchuk.proforghelper.ui.itemView.StudentItemView;
import com.osadchuk.proforghelper.ui.itemView.StudentItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class StudentListAdapter extends BaseAdapter {

    @RootContext
    Context context;
    private List<Student> studentList;

    @AfterInject
    void init() {
        studentList = Student.findAll();
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Student getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentItemView studentItemView;
        if (convertView == null) {
            studentItemView = StudentItemView_.build(context);
        } else {
            studentItemView = (StudentItemView) convertView;
        }
        studentItemView.bind(getItem(position));
        return studentItemView;
    }

    public void refresh() {
        studentList = Student.findAll();
        notifyDataSetChanged();
    }
}
