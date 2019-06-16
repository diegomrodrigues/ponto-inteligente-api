package drsolutions.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import drsolutions.pontointeligente.api.entities.Empresa;

/**
 * Um repositório está vinculado à regra de negócio da aplicação, neste caso, a API,
 * e está associado ao agregado dos seus objetos de negócio e retorna objetos 
 * de domínio que representam esses dados.<br/>
 * <br/>
 * A anotação @Transactional(readOnly = true) identificando que determinada transação 
 * não pode realizar operações de escrita ou alterações, apenas leitura.<br/>
 * <br/>
 * Repositório: EmpresaRepository
 * 
 * @author Diego M. Rodrigues
 *
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
}
