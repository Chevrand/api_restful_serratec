package org.serratec.java02backend.projeto04v2.repository;

import org.serratec.java02backend.projeto04v2.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}