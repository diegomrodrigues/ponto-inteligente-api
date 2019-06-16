package drsolutions.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import drsolutions.pontointeligente.api.entities.Funcionario;

/**
 * Um repositório está vinculado à regra de negócio da aplicação, neste caso, a API,
 * e está associado ao agregado dos seus objetos de negócio e retorna objetos 
 * de domínio que representam esses dados.<br/>
 * <br/>
 * A anotação @Transactional(readOnly = true) identificando que determinada transação 
 * não pode realizar operações de escrita ou alterações, apenas leitura.<br/>
 * <br/>
 * Repositório: LancamentoRepository
 * 
 * @author Diego M. Rodrigues
 *
 */
@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);
	
	Funcionario findByEmail(String email);
	
	Funcionario findByCpfOrEmail(String cpf, String email);
}
