package com.osadchuk.proforghelper.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.ui.adapter.StudentListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_group_list)
public class GroupListActivity extends AppCompatActivity {

    @Bean
    StudentListAdapter adapter;

    @StringRes(R.string.group_list)
    String groupListTitle;

    @ViewById
    FloatingActionButton fabAddStudent;

    @ViewById
    ListView studentsListView;

    private int oldFirsVisibleItem = 0;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(groupListTitle);
        }
        studentsListView.setAdapter(adapter);
        studentsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    oldFirsVisibleItem = view.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > oldFirsVisibleItem) {
                    fabAddStudent.hide();
                } else {
                    fabAddStudent.show();
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

    @Click(R.id.fabAddStudent)
    void onClickFabAddStudent() {
        AddStudentActivity_.intent(GroupListActivity.this).start();
    }

    public void refreshStudentList() {
        adapter.refresh();
    }
}
