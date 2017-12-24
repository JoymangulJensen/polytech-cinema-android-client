package cinema.webservice.polytech.fr.cinemawebservice.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.ui.ActorsFragment;

import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.ui.adapter
 * Date: 24-Dec-17
 * Time: 14:09
 */
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {
    private final List<Actor> actors;
    private final ActorsFragment.OnSelectActor mListener;

    public ActorAdapter(List<Actor> actors, ActorsFragment.OnSelectActor mListener) {
        this.actors = actors;
        this.mListener = mListener;
    }

    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ActorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActorAdapter.ViewHolder holder, int position) {
        holder.mIdView.setText(String.valueOf(actors.get(position).getId()));
        holder.mContentView.setText(String.valueOf(actors.get(position).getFullName()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.actor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Actor actor;

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
