package com.osadchuk.proforghelper.ui.itemView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.Student;
import com.osadchuk.proforghelper.ui.activity.GroupListActivity;
import com.osadchuk.proforghelper.ui.activity.StudentInfoActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.student_item)
public class StudentItemView extends ConstraintLayout {

    @ViewById
    TextView studentName;

    @ViewById
    ImageButton btnStudentInfo;

    @ViewById
    ImageButton btnDeleteStudent;

    private Student student;

    private Context context;

    public StudentItemView(Context context) {
        super(context);
        this.context = context;
    }

    public void bind(Student student) {
        this.student = student;
        studentName.setText(student.getName());
    }

    @Click
    void btnDeleteStudent() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    deleteStudent();
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Вы действительно хотите удалить студента \"" + student.getName() + "\"?")
                .setPositiveButton("Да", dialogClickListener)
                .setNegativeButton("Нет", dialogClickListener)
                .show();
    }

    @Click
    void btnStudentInfo() {
        StudentInfoActivity_.intent(context).extra("studentId", student.getId()).start();
    }

    private void deleteStudent() {
        if (student != null) {
            student.delete();
            student = null;
            if (context instanceof GroupListActivity) {
                ((GroupListActivity) context).refreshStudentList();
            }
        }
    }
}
