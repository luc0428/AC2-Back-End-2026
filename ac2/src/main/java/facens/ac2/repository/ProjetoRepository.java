package facens.ac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import facens.ac2.model.ProjetoModel;
public interface ProjetoRepository extends JpaRepository<ProjetoModel, Long> {

}
