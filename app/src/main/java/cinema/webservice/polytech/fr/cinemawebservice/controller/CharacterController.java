package cinema.webservice.polytech.fr.cinemawebservice.controller;

import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import retrofit2.Call;
import retrofit2.http.GET;

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
    Call<List<Actor>> getCharacters();
}
