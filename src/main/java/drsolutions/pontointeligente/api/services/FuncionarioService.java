package drsolutions.pontointeligente.api.services;

import java.util.Optional;

import drsolutions.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 * Retorna um funcionário dado um CPF
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);

	/**
	 * Retorna um funcionário dado um e-mail
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);

	/**
	 * Retorna um funcionário dado um CPF ou dado um e-mail
	 * 
	 * @param cpf
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpfOuEmail(String cpf, String email);
	
	/**
	 * Retorna um funcionário pelo ID
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);

	/**
	 * Cadastra um novo funcionário no banco de dados
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);
}
