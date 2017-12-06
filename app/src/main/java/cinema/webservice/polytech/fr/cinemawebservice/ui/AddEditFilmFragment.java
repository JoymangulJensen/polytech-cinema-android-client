package cinema.webservice.polytech.fr.cinemawebservice.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFilmFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_FILM_EDIT = "ui.filmfraqgment.edit";

    private Film film;


    public AddEditFilmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param film Parameter 1.
     * @return A new instance of fragment AddEditFilmFragment.
     */
    public static AddEditFilmFragment newInstance(Film film) {
        AddEditFilmFragment fragment = new AddEditFilmFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FILM_EDIT, film);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            film = getArguments().getParcelable(ARG_FILM_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_edit_film, container, false);
        EditText et = (EditText) view.findViewById(R.id.editText);
        if(film !=null)
            et.setText(film.getTitle());

        //*ell the fragment that it has menu options in order to callonCreateOptionsMenu
        setHasOptionsMenu(true);
        return view;
    }

}
