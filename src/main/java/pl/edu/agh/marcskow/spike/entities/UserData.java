package pl.edu.agh.marcskow.spike.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.agh.marcskow.spike.model.UserDataModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class UserData {
    @Id
    @Column(name = "userdata_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    public UserData(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserData(UserDataModel userDataModel) {
        this.email = userDataModel.getEmail();
        this.firstName = userDataModel.getFirstName();
        this.lastName = userDataModel.getLastName();
    }
}
