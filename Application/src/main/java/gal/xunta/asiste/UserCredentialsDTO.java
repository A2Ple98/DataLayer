package gal.xunta.asiste;

import java.util.Locale;

public class UserCredentialsDTO {
    private String username;
    private String password;

    public UserCredentialsDTO(String username, String password) {
        this.username = username.toUpperCase(Locale.ROOT);
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
