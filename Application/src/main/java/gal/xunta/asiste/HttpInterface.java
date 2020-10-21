package gal.xunta.asiste;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpInterface {

    @POST("token/login.json")
    Call<UserDTO> login(@Body UserCredentialsDTO userCredentials);
}
