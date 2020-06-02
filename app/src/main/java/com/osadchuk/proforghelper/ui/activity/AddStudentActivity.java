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
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_add_student)
public class AddStudentActivity extends AppCompatActivity {

    @StringRes(R.string.add_student)
    String addStudentTitle;

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
    Button btnAddStudent;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(addStudentTitle);
        }

    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }

    @Click
    void btnAddStudent() {
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
            Student newStudent = new Student(name, educationForm, memberOfProfComm,
                    fromOtherCity, married, children, familyIssues);
            newStudent.save();
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        }

    }

}
