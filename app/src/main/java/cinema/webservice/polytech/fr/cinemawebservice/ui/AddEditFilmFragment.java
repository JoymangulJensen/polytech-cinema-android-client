package cinema.webservice.polytech.fr.cinemawebservice.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.controller.FilmController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFilmFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_FILM_EDIT = "ui.filmfraqgment.edit";

    private Film film;
    private View view;
    private FilmController filmController;

    public AddEditFilmFragment() {
        // Required empty public constructor
        filmController = CinemaClient.getClient().create(FilmController.class);
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
        view = inflater.inflate(R.layout.fragment_add_edit_film, container, false);
        if (film != null) {
            EditText tv = (EditText) view.findViewById(R.id.tv_title);
            tv.setText(film.getTitle());
            EditText tv2 = (EditText) view.findViewById(R.id.tv_budget);
            tv2.setText(String.valueOf(film.getBudget()));
            EditText tv3 = (EditText) view.findViewById(R.id.tv_grossing);
            tv3.setText(String.valueOf(film.getGrossing()));
            DatePicker tv4 = (DatePicker) view.findViewById(R.id.datePicker);
            if (film.getReleaseDate() != null) {
                DateTime inspected_at = DateTime.parse(film.getReleaseDateStr());
                int year = inspected_at.getYear();
                int month = inspected_at.getMonthOfYear() - 1;      // Need to subtract 1 here.
                int day = inspected_at.getDayOfMonth();
                tv4.updateDate(year, month, day);
            }

            EditText tv5 = (EditText) view.findViewById(R.id.tv_duration);
            tv5.setText(String.valueOf(film.getDuration()));
            EditText tv6 = (EditText) view.findViewById(R.id.tv_director);
            if (film.getDirector() != null)
                tv6.setText(film.getDirector().toString());
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner_category);
            CategoryAdapter categoryAdapter = new CategoryAdapter(this.getContext(), R.id.spinner_category);
            spinner.setAdapter(categoryAdapter);
        }

        //tell the fragment that it has menu options in order to callonCreateOptionsMenu
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add:
                onEditButtonPressed();
                return true;
            case R.id.action_close:
                onEditButtonPressed();
                return true;
            default:
                return false;
        }
    }

    public void onEditButtonPressed() {
        Film film = getUpdatedFilm();
        Call<Film> call = filmController.updateFilm(film.getId(),
                film.getTitle(),
                film.getBudget(),
                film.getDuration(),
                film.getGrossing(),
                film.getReleaseDateStr(),
                1,
                "CO");
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    public Film getUpdatedFilm() {
        Film film = new Film();
        film.setId(this.film.getId());
        EditText et = (EditText) view.findViewById(R.id.tv_title);
        film.setTitle(et.getText().toString());
        et = (EditText) view.findViewById(R.id.tv_budget);
        film.setBudget(Long.parseLong(et.getText().toString()));
        et = (EditText) view.findViewById(R.id.tv_grossing);
        film.setGrossing(Long.parseLong(et.getText().toString()));
        et = (EditText) view.findViewById(R.id.tv_duration);
        film.setDuration(Long.parseLong(et.getText().toString()));
        DatePicker dp = (DatePicker) view.findViewById(R.id.datePicker);
        DateTime dt = new DateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), 0,0);
        film.setReleaseDate(dt.toDate());
        return film;
    }


}
