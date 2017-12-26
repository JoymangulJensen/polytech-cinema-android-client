package cinema.webservice.polytech.fr.cinemawebservice.controller;

import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.model.Characters;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.controller
 * Date: 24-Dec-17
 * Time: 09:33
 */
public interface CharacterController {
    @GET("character")
    Call<List<Characters>> getCharacters();

    @GET("character/actor/{id}")
    Call<List<Characters>> getCharactersByActor(@Path("id") long id);
}
