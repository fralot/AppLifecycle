package br.com.fiap.lyfecycle.model.repository;

import br.com.fiap.lyfecycle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);


}
