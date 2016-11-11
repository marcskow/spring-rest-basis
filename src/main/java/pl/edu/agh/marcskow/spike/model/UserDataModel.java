package pl.edu.agh.marcskow.spike.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import pl.edu.agh.marcskow.spike.entities.UserSpike;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataModel {
    @NotEmpty(message = "username required")
    private String username;

    @NotEmpty(message = "email address required")
    @Pattern(regexp = ".+@.+\\..+",
            message = "invalid email address")
    private String email;

    @NotEmpty(message = "first name required")
    @Size(min = 1, max = 40)
    private String firstName;

    @NotEmpty(message = "last name required")
    @Size(min = 1, max = 40)
    private String lastName;

    public UserDataModel(UserSpike userSpike){
        this.username = userSpike.getUsername();
        this.email = userSpike.getUserData().getEmail();
        this.firstName = userSpike.getUserData().getFirstName();
        this.lastName = userSpike.getUserData().getLastName();
    }
}
