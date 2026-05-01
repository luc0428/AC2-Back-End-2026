package facens.ac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import facens.ac2.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {

}
