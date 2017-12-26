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
import cinema.webservice.polytech.fr.cinemawebservice.controller.CharacterController;
import cinema.webservice.polytech.fr.cinemawebservice.controller.FilmController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Characters;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.CharacterAdapter;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.FilmAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DirectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectorFragment extends Fragment {
    private static final String ARG_DIRECTOR= "ui.directorFragment";

    private Director director;

    private OnFragmentInteractionListener mListener;
    private FilmsFragment.OnListFragmentInteractionListener selectFilmListener;

    public DirectorFragment() {
        // Required empty public constructor
    }

    public static DirectorFragment newInstance(Director director) {
        DirectorFragment fragment = new DirectorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DIRECTOR, director);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            director = getArguments().getParcelable(ARG_DIRECTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_director, container, false);
        if (director != null) {
            TextView tv = (TextView) view.findViewById(R.id.tv_director_title);
            tv.setText(director.getFullName());
            getFilms(view);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            selectFilmListener = (FilmsFragment.OnListFragmentInteractionListener) context;
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

    private void getFilms(final View view) {

        final FilmController filmController = CinemaClient.getClient().create(FilmController.class);
        Call<List<Film>> callFilms = filmController.getFilmsByDirector(director.getId());
        callFilms.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_directors);
                Context context = view.getContext();
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                FilmAdapter filmAdapter = new FilmAdapter(response.body(), selectFilmListener);
                recyclerView.setAdapter(filmAdapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Director director);
    }
}
