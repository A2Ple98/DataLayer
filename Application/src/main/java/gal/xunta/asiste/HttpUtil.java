package gal.xunta.asiste;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {
    public static String BASE_URL = "https://asiste.xunta.gal/asiste-gw/rest/";

    public static HttpInterface httpInterface = new Retrofit.Builder()
            .baseUrl(HttpUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(HttpInterface.class);
}
