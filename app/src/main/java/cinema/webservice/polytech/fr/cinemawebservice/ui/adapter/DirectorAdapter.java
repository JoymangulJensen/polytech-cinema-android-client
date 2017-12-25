package cinema.webservice.polytech.fr.cinemawebservice.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;
import cinema.webservice.polytech.fr.cinemawebservice.ui.DirectorsFragment;

import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.ui.adapter
 * Date: 24-Dec-17
 * Time: 16:11
 */
public class DirectorAdapter extends RecyclerView.Adapter<DirectorAdapter.ViewHolder> {
    private final List<Director> directors;
    private final DirectorsFragment.OnSelectDirector mListener;

    public DirectorAdapter(List<Director> directors, DirectorsFragment.OnSelectDirector mListener) {
        this.directors = directors;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new DirectorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(String.valueOf(directors.get(position).getId()));
        holder.mContentView.setText(String.valueOf(directors.get(position).getFullName()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.director);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return directors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Director director;

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
