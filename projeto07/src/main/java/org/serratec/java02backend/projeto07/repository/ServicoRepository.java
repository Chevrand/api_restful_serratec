package org.serratec.java02backend.projeto07.repository;

import java.util.List;

import org.serratec.java02backend.projeto07.dto.RelatorioDto;
import org.serratec.java02backend.projeto07.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{	
	
	List<Servico> findTop5ByOrderByIdServicoDesc();
	
	@Query(value="select\r\n"
			+ "c2.CLIENTE_TX_NOME as cliente,\r\n"
			+ "c.CARRO_TX_MODELO as modelo,\r\n"
			+ "s.SERVICO_TX_DESCRICAO as descricaoServico,\r\n"
			+ "s.SERVICO_NU_VALOR as valor\r\n"
			+ "from servico s join carro c on(s.servico_carro=c.carro_cd_id)\r\n"
			+ "join cliente c2 on(c2.cliente_cd_id=c.CARRO_CLIENTE_PROPRIETARIO)\r\n"
			+ "order by s.SERVICO_CD_ID \r\n"
			+ "desc\r\n"
			+ "limit 5", nativeQuery=true)
	List<RelatorioDto> relatorio();
}