package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.controller.ActorController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.ActorAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSelectActor} interface
 * to handle interaction events.
 * Use the {@link ActorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActorsFragment extends Fragment {

    private OnSelectActor mListener;
    private ActorController actorController;

    public ActorsFragment() {
        actorController = CinemaClient.getClient().create(ActorController.class);
    }

    public static ActorsFragment newInstance() {
        ActorsFragment fragment = new ActorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            Call<List<Actor>> call = actorController.getActors();
            call.enqueue(new Callback<List<Actor>>() {
                @Override
                public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                    recyclerView.setAdapter(new ActorAdapter(response.body(), mListener));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    call.cancel();
                }
            });


        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectActor) {
            mListener = (OnSelectActor) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSelectActor");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSelectActor {
        void onFragmentInteraction(Actor actor);
    }
}
