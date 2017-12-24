package cinema.webservice.polytech.fr.cinemawebservice.controller;

import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 06-Dec-17.
 */
public interface FilmController {
    @GET("film")
    Call<List<Film>> getFilms();

    @FormUrlEncoded
    @POST("film")
    Call<Film> addFilm(
                          @Field("title") String title,
                          @Field("budget") long budget,
                          @Field("duration") long duration,
                          @Field("grossing") long grossing,
                          @Field("releaseDate") String releaseDate,
                          @Field("director") long director,
                          @Field("category") String category);

    @FormUrlEncoded
    @PUT("film/{id}")
    Call<Film> updateFilm(@Path("id") long id,
                          @Field("title") String title,
                          @Field("budget") long budget,
                          @Field("duration") long duration,
                          @Field("grossing") long grossing,
                          @Field("releaseDate") String releaseDate,
                          @Field("director") long director,
                          @Field("category") String category);

    @DELETE("film/{id}")
    Call<Void> deleteFilm(@Path("id") long id);
}
