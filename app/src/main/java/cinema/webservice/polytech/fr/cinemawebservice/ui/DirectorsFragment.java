package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.controller.ActorController;
import cinema.webservice.polytech.fr.cinemawebservice.controller.DirectorController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.ActorAdapter;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.DirectorAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectorsFragment.OnSelectDirector} interface
 * to handle interaction events.
 * Use the {@link DirectorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectorsFragment extends Fragment {
    private OnSelectDirector mListener;
    private DirectorController directorController;

    public DirectorsFragment() {
        directorController = CinemaClient.getClient().create(DirectorController.class);
    }

    public static DirectorsFragment newInstance() {
        DirectorsFragment fragment = new DirectorsFragment();
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

            Call<List<Director>> call = directorController.getDirector();
            call.enqueue(new Callback<List<Director>>() {
                @Override
                public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                    recyclerView.setAdapter(new DirectorAdapter(response.body(), mListener));
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
        if (context instanceof OnSelectDirector) {
            mListener = (OnSelectDirector) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSelectDirector {
        void onFragmentInteraction(Director director);
    }
}
