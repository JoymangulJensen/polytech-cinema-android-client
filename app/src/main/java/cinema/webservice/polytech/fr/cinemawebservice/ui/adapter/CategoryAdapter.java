package cinema.webservice.polytech.fr.cinemawebservice.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Category;
import cinema.webservice.polytech.fr.cinemawebservice.ui.CategoriesFragment;

import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.ui.adapter
 * Date: 24-Dec-17
 * Time: 15:03
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {
    private final List<Category> category;
    private final CategoriesFragment.OnSelectCategory mListener;

    public CategoryAdapter(List<Category> category, CategoriesFragment.OnSelectCategory mListener) {
        this.category = category;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(String.valueOf(category.get(position).getCode()));
        holder.mContentView.setText(String.valueOf(category.get(position).getName()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Category category;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
