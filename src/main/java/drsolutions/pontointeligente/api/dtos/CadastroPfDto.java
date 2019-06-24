package drsolutions.pontointeligente.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 * DTO para o cadastro de uma PF (Pessoa Física - Funcionário de uma empresa) na API<br/>
 * <br/>
 * As <strong>regras de validação</strong> estão nessa classe, com anotações como:<br/>
 * @NotEmpty<br/>
 * @Length<br/>
 * @Email<br/>
 * @CPF<br/>
 * @CNPJ<br/>
 * <br/>
 * Requisições RESTful exigem na maioria dos casos o envio de parâmetros,
 * sejam eles de formulários,​ ​configuração.<br/>
 * <br/>
 * Para que esses dados sejam facilmente manipulados e gerenciados pelo Spring,
 * é recomendada a utilização do padrão de projetos DTO (Data Transfer
 * Object), por permitir que os dados utilizados em uma requisição, sejam
 * facilmente convertidos para uma classe Java.<br/>
 * <br/>
 * Sua grande vantagem é permitir a fácil manipulação dos dados da
 * requisição HTTP, e os DTOs consistem apenas de classes Java com atributos,
 * que representam os parâmetros das​ ​requisições.
 * 
 * @author Diego M. Rodrigues
 *
 */
public class CadastroPfDto {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	
	/* Valores opcionais */
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasTrabalhadasDia = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();
	
	private String cnpj;
	
	public CadastroPfDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "E-mail não pode ser vazio")
	@Length(min = 5, max = 200, message = "E-mail deve conter entre 3 e 200 caracteres")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Senha não pode ser vazia")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@NotEmpty(message = "CPF não pode ser vazio")
	@CPF(message = "CPF inválido")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasTrabalhadasDia() {
		return qtdHorasTrabalhadasDia;
	}

	public void setQtdHorasTrabalhadasDia(Optional<String> qtdHorasTrabalhadasDia) {
		this.qtdHorasTrabalhadasDia = qtdHorasTrabalhadasDia;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@NotEmpty(message = "CNPJ não pode ser vazio")
	@CNPJ(message = "CNPJ inválido")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPfDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhadasDia=" + qtdHorasTrabalhadasDia
				+ ", qtdHorasAlmoco=" + qtdHorasAlmoco + ", cnpj=" + cnpj + "]";
	}
}
