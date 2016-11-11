package pl.edu.agh.marcskow.spike.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.marcskow.spike.entities.UserData;
import pl.edu.agh.marcskow.spike.entities.UserSpike;
import pl.edu.agh.marcskow.spike.model.UserDataModel;
import pl.edu.agh.marcskow.spike.repositories.UserRepository;
import pl.edu.agh.marcskow.spike.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserSpike> findUserById(Long id) {
        Optional<UserSpike> user = Optional.ofNullable(userRepository.findOne(id));
        return user.isPresent() ? user : Optional.empty();
    }

    @Override
    public Optional<UserSpike> findUserByUsername(String username){
        Optional<UserSpike> user = Optional.ofNullable(userRepository.findUserByUsername(username));
        return user.isPresent() ? user : Optional.empty();
    }

    @Override
    public Optional<UserSpike> findUserByEmail(String email){
        Optional<UserSpike> user = Optional.ofNullable(userRepository.findUserByUserData_Email(email));
        return user.isPresent() ? user : Optional.empty();
    }

    @Override
    public boolean addUser(UserSpike userToSave) {
        Optional<UserSpike> user = Optional.ofNullable(userRepository.findUserByUserData_Email(userToSave.getUserData().getEmail()));
        if (user.isPresent()) {
            return false;
        } else {
            userRepository.save(userToSave);
            return true;
        }
    }

    @Override
    public Optional<UserDataModel> editUser(UserSpike user, @Valid UserDataModel newUserDataModel) {
        UserData newUserData = new UserData(newUserDataModel);
        String newEmail = newUserData.getEmail().toLowerCase().trim();
        if (findUserByEmail(newEmail).isPresent())
            return Optional.empty();
        user.setUserData(newUserData);
        userRepository.save(user);
        return Optional.ofNullable(newUserDataModel);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<UserSpike> user = Optional.ofNullable(userRepository.findOne(id));
        if (user.isPresent()) {
            userRepository.delete(id);
            return true;
        } else {
            return false;
        }
    }
}
