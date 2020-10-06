package br.com.gerenciamentoprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamentoprojetos.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}
