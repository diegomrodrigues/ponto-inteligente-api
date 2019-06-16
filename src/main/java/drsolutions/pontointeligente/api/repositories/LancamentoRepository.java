package drsolutions.pontointeligente.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import drsolutions.pontointeligente.api.entities.Lancamento;

/**
 * Um repositório está vinculado à regra de negócio da aplicação, neste caso, a API,
 * e está associado ao agregado dos seus objetos de negócio e retorna objetos 
 * de domínio que representam esses dados.<br/>
 * <br/>
 * A anotação @Transactional(readOnly = true) identificando que determinada transação 
 * não pode realizar operações de escrita ou alterações, apenas leitura.<br/>
 * <br/>
 * A anotação @NamedQueries(...) define consultas Java Persistence estáticas e pré-definidas.<br/>
 * <br/>
 * Repositório: LancamentoRepository
 * 
 * @author Diego M. Rodrigues
 *
 */
@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "LancamentoRepository.findByFuncionarioId",
			query = "SELECT lanc FROM lancamento lanc WHERE lanc.funcionario.id = :funcionarioId")})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
	
	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);
}
