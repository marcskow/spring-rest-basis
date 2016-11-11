package pl.edu.agh.marcskow.spike.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.marcskow.spike.entities.UserSpike;

@Repository
public interface UserRepository extends JpaRepository<UserSpike, Long> {

    UserSpike findUserByUserData_Email(String email);
    UserSpike findUserByUsername(String username);
    UserSpike findUserById(Long id);

    Long countUserByUserData_email(String email);
}