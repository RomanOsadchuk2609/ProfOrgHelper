package com.osadchuk.proforghelper.ui.itemView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.MathHelp;
import com.osadchuk.proforghelper.model.Student;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

@EViewGroup(R.layout.math_help_item)
public class MathHelpItemView extends ConstraintLayout {

    @ViewById
    TextView studentName;
    @ViewById
    Spinner statusSpinner;
    @ViewById
    ImageView imageStatus;
    private Map<Integer, Integer> statusImageMap;
    private Context context;

    private Student student;

    private MathHelp mathHelp;

    private String year;

    private String month;

    public MathHelpItemView(Context context) {
        super(context);
        this.context = context;
    }

    @AfterViews
    void init() {
        statusImageMap = new HashMap<>();
        statusImageMap.put(0, R.drawable.circle);
        statusImageMap.put(1, R.drawable.request);
        statusImageMap.put(2, R.drawable.check);

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mathHelp.setStatus(position);
                mathHelp.save();
                Integer resource = statusImageMap.get(position);
                if (resource != null) {
                    imageStatus.setBackgroundResource(resource);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });
    }

    public void bind(Student student, String year, String month) {
        this.student = student;
        studentName.setText(student.getName());
        mathHelp = MathHelp.findByStudentAndYearAndMonth(this.student, year, month);
        if (mathHelp == null) {
            mathHelp = new MathHelp(this.student, year, month, 0);
            mathHelp.save();
        }
        statusSpinner.setSelection(mathHelp.getStatus());
    }
}
