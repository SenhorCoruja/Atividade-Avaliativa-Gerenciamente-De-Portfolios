package br.com.gerenciamentoprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamentoprojetos.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
