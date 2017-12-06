package cinema.webservice.polytech.fr.cinemawebservice.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 06-Dec-17.
 */
public class CinemaClient {
    private final static  String BASE_URL = "http://192.168.1.50:8080//cinema/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
