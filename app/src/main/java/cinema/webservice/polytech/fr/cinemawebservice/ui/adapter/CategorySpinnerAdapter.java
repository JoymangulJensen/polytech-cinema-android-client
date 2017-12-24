package cinema.webservice.polytech.fr.cinemawebservice.ui.adapter;

import android.content.Context;
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
public class CategorySpinnerAdapter extends ArrayAdapter<Category> {
    private List<Category> items = new ArrayList<>();
    private Context context;

    public CategorySpinnerAdapter(Context context, int resouceId, List<Category> list) {
        super(context, resouceId);
        this.items = list;
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
        View v = inflater.inflate(R.layout.spinner_item, null, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.mContentView1.setText(items.get(position).getCode());
        viewHolder.mContentView2.setText(items.get(position).getName());
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
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
