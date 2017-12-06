package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.ui.FilmsFragment.OnListFragmentInteractionListener;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Film} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private final List<Film> films;
    private final OnListFragmentInteractionListener mListener;

    FilmAdapter(List<Film> films, OnListFragmentInteractionListener listener) {
        this.films = films;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.film = films.get(position);
        holder.mIdView.setText(String.valueOf(films.get(position).getId()));
        holder.mContentView.setText(String.valueOf(films.get(position).getTitle()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.film);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Film film;

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
