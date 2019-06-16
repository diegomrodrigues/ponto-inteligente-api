package drsolutions.pontointeligente.api.services;

import java.util.Optional;

import drsolutions.pontointeligente.api.entities.Empresa;

/**
 * Interface para a implementação do serviço EmpresaServiceImpl.
 * 
 * @author Diego M. Rodrigues
 *
 */
public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
}
