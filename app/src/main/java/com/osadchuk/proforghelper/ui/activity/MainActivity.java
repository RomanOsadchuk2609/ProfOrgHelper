package com.osadchuk.proforghelper.ui.activity;

import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.osadchuk.proforghelper.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    Button btnGroupList;

    @ViewById
    Button btnMathQueue;

    @ViewById
    Button btnInputForm;

    @ViewById
    Button btnNotes;

    @AfterViews
    void init() {
        ActiveAndroid.initialize(this);
    }

    @Click(R.id.btnGroupList)
    void onClickBtnGroupList() {
        GroupListActivity_.intent(MainActivity.this).start();
    }

    @Click(R.id.btnMathQueue)
    void onClickBtnMathQueue() {
        Toast.makeText(this, "Math Queue", Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.btnInputForm)
    void onClickBtnInputForm() {
        MathFormActivity_.intent(MainActivity.this).start();
    }

    @Click(R.id.btnNotes)
    void onClickBtnNotes() {
        NotesListActivity_.intent(MainActivity.this).start();
    }

}
