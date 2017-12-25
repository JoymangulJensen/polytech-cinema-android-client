package cinema.webservice.polytech.fr.cinemawebservice.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import cinema.webservice.polytech.fr.cinemawebservice.R;
import cinema.webservice.polytech.fr.cinemawebservice.controller.CategoryController;
import cinema.webservice.polytech.fr.cinemawebservice.controller.DirectorController;
import cinema.webservice.polytech.fr.cinemawebservice.controller.FilmController;
import cinema.webservice.polytech.fr.cinemawebservice.model.Category;
import cinema.webservice.polytech.fr.cinemawebservice.model.Director;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import cinema.webservice.polytech.fr.cinemawebservice.retrofit.CinemaClient;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.CategorySpinnerAdapter;
import cinema.webservice.polytech.fr.cinemawebservice.ui.adapter.DirectionSpinnerAdapter;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFilmFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_FILM_EDIT = "ui.filmfraqgment.edit";
    Spinner directorSpinner;
    private Spinner categorySpinner;
    private Film film;
    private boolean isNewFilm = true;
    private View view;
    private FilmController filmController;
    private Context context;
    private FilmsFragment.OnListFragmentInteractionListener mListener;
    private OnReturnToFilmsListener returntoFilmsListener;


    public AddEditFilmFragment() {
        // Required empty public constructor
        filmController = CinemaClient.getClient().create(FilmController.class);
        if(film != null)
            isNewFilm = false;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FilmsFragment.OnListFragmentInteractionListener) {
            mListener = (FilmsFragment.OnListFragmentInteractionListener) context;
            returntoFilmsListener = (OnReturnToFilmsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        returntoFilmsListener = null;
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
        }
        getDirector();
        getCategory();


        //tell the fragment that it has menu options in order to callonCreateOptionsMenu
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        if(film != null)
            menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add:
                if(isNewFilm)
                    onAddButtonPressed();
                else
                    onEditButtonPressed();
                return true;
            case R.id.action_close:
                deleteFilm();
                return true;
            default:
                return false;
        }
    }

    private void onEditButtonPressed() {
        Film film = getUpdatedFilm();
        Call<Film> call = filmController.updateFilm(film.getId(),
                film.getTitle(),
                film.getBudget(),
                film.getDuration(),
                film.getGrossing(),
                film.getReleaseDateStr(),
                film.getDirector().getId(),
                film.getCategory().getCode());

        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                mListener.onListFragmentInteraction(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void onAddButtonPressed() {
        Film film = getUpdatedFilm();
        Call<Film> call = filmController.addFilm(
                film.getTitle(),
                film.getBudget(),
                film.getDuration(),
                film.getGrossing(),
                film.getReleaseDateStr(),
                film.getDirector().getId(),
                film.getCategory().getCode());

        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                mListener.onListFragmentInteraction(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }


    private Film getUpdatedFilm() {
        Film film = new Film();
        if(this.film != null)
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
        DateTime dt = new DateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), 0, 0);
        film.setReleaseDate(dt.toDate());
        Category selectedCategory = (Category) categorySpinner.getSelectedItem();
        film.setCategory(selectedCategory);
        Director selectedDirector = (Director) directorSpinner.getSelectedItem();
        film.setDirector(selectedDirector);
        return film;
    }

    private void deleteFilm() {
        Call<Void> callDirector = filmController.deleteFilm(film.getId());
        context = this.getContext();
        callDirector.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                returntoFilmsListener.OnReturnToFilms();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void getCategory() {
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CategoryController categoryController = CinemaClient.getClient().create(CategoryController.class);
        Call<List<Category>> call = categoryController.getCategory();
        context = this.getContext();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                CategorySpinnerAdapter categoryAdapter = new CategorySpinnerAdapter(context, R.id.spinner_category, response.body());
                categorySpinner.setAdapter(categoryAdapter);
                int currentCategory = 0;
                if(!isNewFilm) {
                    for (int i = 0; i < categoryAdapter.getCount(); i++) {
                        if (film.getCategory().getCode().equals(categoryAdapter.getItem(i).getCode())) {
                            currentCategory = i;
                            break;
                        }
                    }
                }

                categorySpinner.setSelection(currentCategory);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
    private void getDirector() {
        directorSpinner = (Spinner) view.findViewById(R.id.spinner_director);
        DirectorController directorController = CinemaClient.getClient().create(DirectorController.class);
        Call<List<Director>> callDirector = directorController.getDirector();
        context = this.getContext();
        callDirector.enqueue(new Callback<List<Director>>() {
            @Override
            public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                DirectionSpinnerAdapter directionAdapter = new DirectionSpinnerAdapter(context, R.id.spinner_director, response.body());
                directorSpinner.setAdapter(directionAdapter);
                int currentDirector = 0;
                if(!isNewFilm) {
                    for (int i = 0; i < directionAdapter.getCount(); i++) {
                        if (film.getDirector().getId() == directionAdapter.getItem(i).getId()) {
                            currentDirector = i;
                            break;
                        }
                    }
                }
                directorSpinner.setSelection(currentDirector);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    public interface OnReturnToFilmsListener {
        void OnReturnToFilms();
    }
}
