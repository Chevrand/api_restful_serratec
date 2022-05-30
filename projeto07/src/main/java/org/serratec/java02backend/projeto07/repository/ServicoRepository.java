package org.serratec.java02backend.projeto07.repository;

import java.util.List;

import org.serratec.java02backend.projeto07.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{	
	
	List<Servico> findTop5ByOrderByIdServicoDesc();
}