package com.ufcspa.unasus.appportfolio.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ufcspa.unasus.appportfolio.Model.Annotation;
import com.ufcspa.unasus.appportfolio.Model.Reference;
import com.ufcspa.unasus.appportfolio.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Desenvolvimento on 04/01/2016.
 */
public class AnnotationAdapter extends BaseAdapter {
    private TextView annotation;
    private List<Annotation> annotations;
    private Context context;
    private static LayoutInflater inflater = null;



    public AnnotationAdapter(Context ctxt,List annotations){
        this.context=ctxt;
        this.annotations=annotations;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void add(Annotation obj) {
        annotations.add(obj);
    }
    public void delete(int position){
        annotations.remove(position);
    }


    public int getCount() {
        return this.annotations.size();
    }

    public Annotation getItem(int index) {
        return this.annotations.get(index);
    }

    @Override
    public long getItemId(int position) {
        return (long)annotations.get(position).getIdAnnotation();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.row_layout, null);


        Annotation a = getItem(position);

        annotation = (TextView) rowView.findViewById(R.id.ref_item_txt_url);
        annotation.setText(a.getDsAnnotation());
        return rowView;
    }

    public void clearAdapter(){
        annotations.clear();
        notifyDataSetChanged();
    }

    public void refresh(List a){
        this.annotations=a;
    }
}
