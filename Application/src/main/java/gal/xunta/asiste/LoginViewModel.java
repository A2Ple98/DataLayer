package gal.xunta.asiste;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.regex.Pattern;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Getter
public class LoginViewModel extends AndroidViewModel {
    private final MutableLiveData<String> username;
    private final MutableLiveData<String> password;
    private final MutableLiveData<Boolean> error;
    private final HttpInterface httpInterface;
    private final MutableLiveData<User> loggedIn;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.httpInterface = HttpUtil.httpInterface;
        this.username = new MutableLiveData<>();
        this.password = new MutableLiveData<>();
        this.error = new MutableLiveData<>(false);
        this.loggedIn = new MutableLiveData<>();
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.length() > 0;
    }

    public boolean isUsernameValid(String username) {
        return username != null && Pattern.compile("[XYZ0-9]\\d{7}[A-Z]")
                .matcher(username).matches();
    }

    public void login(String username, String password) {
        this.httpInterface.login(new UserCredentialsDTO(username, password))
                .enqueue(new Callback<UserDTO>() {
                    @Override
                    public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                        if (response.code() == 200) {
                            error.postValue(false);
                            SharedPreferences.Editor editor = getApplication().getSharedPreferences("asiste_data", Context.MODE_PRIVATE).edit();
                            editor.putString("user", new Gson().toJson(response.body().getUser(), User.class));
                            editor.apply();
                            loggedIn.postValue(response.body().getUser());
                        } else {
                            onFailure(call, new Throwable());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDTO> call, Throwable t) {
                        error.postValue(true);
                    }
                });
    }
}
