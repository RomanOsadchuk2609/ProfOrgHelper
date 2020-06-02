package com.osadchuk.proforghelper.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.ui.adapter.MathHelpAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_math_help)
public class MathHelpActivity extends AppCompatActivity {

    @StringRes(R.string.math_help)
    String mathHelpTitle;

    @ViewById
    Spinner spinnerYear;

    @ViewById
    Spinner spinnerMonth;

    @ViewById
    ListView mathHelpListView;

    @Bean
    MathHelpAdapter mathHelpAdapter;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mathHelpTitle);
        }
        mathHelpAdapter.setDate(spinnerYear.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString());
        mathHelpListView.setAdapter(mathHelpAdapter);

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mathHelpAdapter.setDate(spinnerYear.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mathHelpAdapter.setDate(spinnerYear.getSelectedItem().toString(), spinnerMonth.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }
}
