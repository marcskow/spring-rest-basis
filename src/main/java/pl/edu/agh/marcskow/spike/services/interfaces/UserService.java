package pl.edu.agh.marcskow.spike.services.interfaces;


import pl.edu.agh.marcskow.spike.entities.UserSpike;
import pl.edu.agh.marcskow.spike.model.UserDataModel;

import java.util.Optional;

public interface UserService {

    Optional<UserSpike> findUserById(Long id);

    Optional<UserSpike> findUserByEmail(String email);

    Optional<UserSpike> findUserByUsername(String username);

    boolean addUser(UserSpike user);

    Optional<UserDataModel> editUser(UserSpike user, UserDataModel newUserDataModel);

    boolean deleteUser(Long id);
}
