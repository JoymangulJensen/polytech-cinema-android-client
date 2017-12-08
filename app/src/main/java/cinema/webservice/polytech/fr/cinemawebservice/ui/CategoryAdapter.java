package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 08-Dec-17.
 */
public class CategoryAdapter extends ArrayAdapter<Category> {
    private List<Category> items = new ArrayList<>();
    private Context context;

    public CategoryAdapter(Context context, int resouceId) {
        super(context, resouceId);
        Log.d("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", String.valueOf(resouceId));
        Category cat = new Category();
        cat.setCode("XX");
        cat.setName("affffff");
        this.items.add(cat);
        Category cat2 = new Category();
        cat2.setCode("XX");
        cat2.setName("bffffff");
        this.items.add(cat2);
        cat.setCode("XX");
        cat.setName("cffffff");
        this.items.add(cat);
        cat.setCode("XX");
        cat.setName("dffffff");
        this.items.add(cat);
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Category getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View  v = inflater.inflate(R.layout.spinner_item, null, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.mContentView1.setText(items.get(position).getCode());
        viewHolder.mContentView2.setText(items.get(position).getName());
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.spinner_item, null, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.mContentView1.setText(items.get(position).getCode());
        viewHolder.mContentView2.setText(items.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        final View mView;
        final TextView mContentView1;
        final TextView mContentView2;

        ViewHolder(View view) {
            mView = view;
            mContentView1 = (TextView) view.findViewById(R.id.textView1);
            mContentView2 = (TextView) view.findViewById(R.id.textView2);
        }
    }
}