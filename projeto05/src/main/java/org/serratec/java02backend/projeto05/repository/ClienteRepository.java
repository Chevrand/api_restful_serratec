package org.serratec.java02backend.projeto05.repository;

import org.serratec.java02backend.projeto05.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
