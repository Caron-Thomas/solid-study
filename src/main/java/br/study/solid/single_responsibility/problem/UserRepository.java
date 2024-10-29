package br.study.solid.single_responsibility.problem;

import br.study.solid.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findById(long id);
}
