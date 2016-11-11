package pl.edu.agh.marcskow.spike.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class UserSpike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userdata_id")
    private UserData userData;

    public UserSpike(UserData userData, String username, String password){
        this.userData = userData;
        this.username = username;
        this.password = password;
    }
}
