package mx.edu.utez.sguomm10b.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {

Optional<UserEntity> findByEmail(String email);



}
