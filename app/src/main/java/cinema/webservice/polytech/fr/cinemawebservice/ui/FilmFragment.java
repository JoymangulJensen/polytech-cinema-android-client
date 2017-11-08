package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link FilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmFragment extends Fragment {
    // the fragment get the film that the user has selected
    protected static final String ARG_FILM = "ui.filmfraqgment";

    private Film film;

    private OnFragmentInteractionListener mListener;

    public FilmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param film Parameter 1.
     * @return A new instance of fragment FilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilmFragment newInstance(Film film) {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FILM, film);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            film = getArguments().getParcelable(ARG_FILM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_film_title);
        if(film !=null)
            tv.setText(film.getTitle());

        //*ell the fragment that it has menu options in order to callonCreateOptionsMenu
        setHasOptionsMenu(true);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onEditButtonPressed(Film film) {
        if (mListener != null) {
            mListener.onFragmentInteraction(film);
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
            onEditButtonPressed(film);
            return true;
        }

        return false;
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
        void onFragmentInteraction(Film film);
    }
}
