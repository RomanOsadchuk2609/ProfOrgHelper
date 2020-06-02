package com.osadchuk.proforghelper.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.osadchuk.proforghelper.model.Student;
import com.osadchuk.proforghelper.ui.itemView.MathHelpItemView;
import com.osadchuk.proforghelper.ui.itemView.MathHelpItemView_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class MathHelpAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Student> studentList;

    private String month = "";

    private String year = "";

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
        MathHelpItemView mathHelpView = null;
        if (convertView == null) {
            mathHelpView = MathHelpItemView_.build(context);
        } else {
            mathHelpView = (MathHelpItemView) convertView;
        }
        mathHelpView.bind(getItem(position), year, month);
        return mathHelpView;
    }

    public void refresh() {
        studentList = Student.findAll();
        notifyDataSetChanged();
    }

    public void setDate(String year, String month) {
        this.year = year;
        this.month = month;
        notifyDataSetChanged();
    }


}
