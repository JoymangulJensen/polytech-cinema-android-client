package cinema.webservice.polytech.fr.cinemawebservice.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;

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
        }
        return view;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Director director);
    }
}
