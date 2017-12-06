package cinema.webservice.polytech.fr.cinemawebservice.controller;

import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 06-Dec-17.
 */
public interface FilmController {
    @GET("film")
    Call<List<Film>> getFilms();
}
