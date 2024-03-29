package org.serratec.java02backend.projeto05.repository;

import org.serratec.java02backend.projeto05.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer>{

}
