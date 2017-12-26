package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.controller.CharacterController;
import cinema.webservice.polytech.fr.cinemawebservice.controller.DirectorController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.model.Characters;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.CharacterAdapter;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.DirectionSpinnerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ACTOR = "ui.actorFragment";

    private Actor actor;

    private OnFragmentInteractionListener mListener;

    public ActorFragment() {
        // Required empty public constructor
    }


    public static ActorFragment newInstance(Actor actor) {
        ActorFragment fragment = new ActorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ACTOR, actor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actor = getArguments().getParcelable(ARG_ACTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actor, container, false);
        if (actor != null) {
            TextView tv = (TextView) view.findViewById(R.id.tv_actor_title);
            tv.setText(actor.getFullName());
            TextView tv2 = (TextView) view.findViewById(R.id.tv_name);
            tv2.setText(String.valueOf(actor.getName()));
            TextView tv3 = (TextView) view.findViewById(R.id.tv_first_name);
            tv3.setText(String.valueOf(actor.getFirstName()));
            TextView tv4 = (TextView) view.findViewById(R.id.tv_birthday);
            tv4.setText(actor.getBirthdayStr());
            TextView tv5 = (TextView) view.findViewById(R.id.tv_death_date);
            if (actor.getDeathDate() != null)
                tv5.setText(actor.getDeathDateStr());

            getCharacters(view);
        }


        //*ell the fragment that it has menu options in order to callonCreateOptionsMenu
        setHasOptionsMenu(true);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onEditButtonPressed(Actor actor) {
        if (mListener != null) {
            mListener.onFragmentInteraction(actor);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        MenuItem item = menu.getItem(0);
        item.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onEditButtonPressed(actor);
            return true;
        }
        return false;
    }

    private void getCharacters(final View view) {

        final CharacterController characterController = CinemaClient.getClient().create(CharacterController.class);
        Call<List<Characters>> callCharacters = characterController.getCharactersByActor(actor.getId());
        callCharacters.enqueue(new Callback<List<Characters>>() {
            @Override
            public void onResponse(Call<List<Characters>> call, Response<List<Characters>> response) {
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_characters);
                Context context = view.getContext();
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                CharacterAdapter characterAdapter = new CharacterAdapter(response.body());
                recyclerView.setAdapter(characterAdapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
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
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Actor actor);
    }
}
