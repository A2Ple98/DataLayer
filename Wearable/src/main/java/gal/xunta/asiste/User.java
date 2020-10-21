package gal.xunta.asiste;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private String id;
    private String username;
    private String name;
    private String surname1;
    private String surname2;
    private String phoneNumner;
    private String locale;
    private String state;
    private String token;
    private boolean hasGroups;
    private boolean hasDevices;
    private boolean hasRoles;


}
