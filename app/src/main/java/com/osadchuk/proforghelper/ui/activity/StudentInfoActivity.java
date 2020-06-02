package com.osadchuk.proforghelper.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.Student;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_student_info)
public class StudentInfoActivity extends AppCompatActivity {

    @StringRes(R.string.student_info)
    String studentInfoTitle;

    @StringArrayRes(R.array.education_forms)
    String[] educationForms;

    @Extra
    long studentId;

    @ViewById
    EditText editTextName;

    @ViewById
    EditText editTextChildren;

    @ViewById
    EditText editTextFamilyIssues;

    @ViewById
    CheckBox checkBoxProfComm;

    @ViewById
    CheckBox checkBoxMarried;

    @ViewById
    CheckBox checkBoxOtherCity;

    @ViewById
    Spinner spinnerEducationForm;

    @ViewById
    Button btnSaveStudent;

    private Student student;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(studentInfoTitle);
        }
        initStudent();
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }

    @Click
    void btnSaveStudent() {
        String name = editTextName.getText().toString();
        String educationForm = spinnerEducationForm.getSelectedItem().toString();
        boolean memberOfProfComm = checkBoxProfComm.isChecked();
        boolean fromOtherCity = checkBoxOtherCity.isChecked();
        boolean married = checkBoxMarried.isChecked();
        String children = editTextChildren.getText().toString();
        String familyIssues = editTextFamilyIssues.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.input_name, Toast.LENGTH_SHORT).show();
        } else {
            student.setName(name);
            student.setEducationForm(educationForm);
            student.setMemberOfProfComm(memberOfProfComm);
            student.setFromOtherCity(fromOtherCity);
            student.setMarried(married);
            student.setChildren(children);
            student.setFamilyIssues(familyIssues);
            student.save();
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        }
    }

    private void initStudent() {
        student = Student.findById(studentId);
        if (student != null) {
            editTextName.setText(student.getName());
            editTextChildren.setText(student.getChildren());
            editTextFamilyIssues.setText(student.getFamilyIssues());
            checkBoxMarried.setChecked(student.isMarried());
            checkBoxOtherCity.setChecked(student.isFromOtherCity());
            checkBoxProfComm.setChecked(student.isMemberOfProfComm());
            int educationFormIndex = getEducationFormIndex(student);
            if (educationFormIndex >= 0) {
                spinnerEducationForm.setSelection(educationFormIndex);
            }

        }
    }

    private int getEducationFormIndex(Student student) {
        for (int i = 0; i < educationForms.length; i++) {
            if (educationForms[i].equals(student.getEducationForm())) {
                System.out.println(educationForms[i]);
                return i;
            }
        }
        return -1;
    }

}
