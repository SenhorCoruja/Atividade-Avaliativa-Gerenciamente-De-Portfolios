package br.com.gerenciamentoprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamentoprojetos.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	public List findByCargo(String cargo);
	public List findByCargoNot(String cargo);

}
