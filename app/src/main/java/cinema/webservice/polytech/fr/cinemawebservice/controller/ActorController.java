package cinema.webservice.polytech.fr.cinemawebservice.controller;

import cinema.webservice.polytech.fr.cinemawebservice.model.Actor;
import cinema.webservice.polytech.fr.cinemawebservice.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * User: seljo
 * Project: CinemaWebservice
 * Package: cinema.webservice.polytech.fr.cinemawebservice.controller
 * Date: 24-Dec-17
 * Time: 09:32
 */
public interface ActorController {
    @GET("actor")
    Call<List<Actor>> getActors();
}
