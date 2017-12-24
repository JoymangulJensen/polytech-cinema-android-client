package cinema.webservice.polytech.fr.cinemawebservice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;

import java.util.ArrayList;
import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.ui.adapter
 * Date: 24-Dec-17
 * Time: 10:29
 */
public class DirectionAdapter extends ArrayAdapter<Director> {
    private List<Director> items = new ArrayList<>();
    private Context context;

    public DirectionAdapter(Context context, int resouceId, List<Director> list) {
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
    public Director getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  v = inflater.inflate(R.layout.spinner_item, null, false);
        DirectionAdapter.ViewHolder viewHolder = new DirectionAdapter.ViewHolder(v);
        viewHolder.mContentView1.setText(String.valueOf(items.get(position).getId()));
        viewHolder.mContentView2.setText(items.get(position).toString());
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.spinner_item, null, false);
        }
        DirectionAdapter.ViewHolder viewHolder = new DirectionAdapter.ViewHolder(convertView);
        viewHolder.mContentView1.setText(String.valueOf(items.get(position).getId()));
        viewHolder.mContentView2.setText(items.get(position).toString());
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
