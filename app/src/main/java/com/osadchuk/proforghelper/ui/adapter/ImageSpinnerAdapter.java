package com.osadchuk.proforghelper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.osadchuk.proforghelper.R;

import org.androidannotations.annotations.EBean;

import java.util.Arrays;
import java.util.List;

@EBean
public class ImageSpinnerAdapter extends BaseAdapter {

    LayoutInflater mInflater;

    private List<Integer> data = Arrays.asList(R.drawable.circle, R.drawable.request, R.drawable.check);

    public ImageSpinnerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.image_spinner_item, null);
            convertView = mInflater.inflate(R.layout.image_spinner_item, parent);
        }
        convertView.findViewById(R.id.imageStatus).setBackgroundResource(data.get(position));
        return convertView;
    }
}
