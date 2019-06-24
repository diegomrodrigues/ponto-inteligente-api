package drsolutions.pontointeligente.api.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 * DTO para o cadastro de uma PJ (Pessoa Jurídica) na API<br/>
 * Teremos o cadastro de um Funcionário e uma Empresa.<br/>
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
public class CadastroPjDto {
	private Long id;

	/* Pessoa Física */
	private String nome;
	private String email;
	private String senha;
	private String cpf;

	/* Empresa */
	private String razaoSocial;
	private String cnpj;

	public CadastroPjDto() {
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
	@Length(min = 5, max = 200, message = "E-mail deve conter entre 5 e 200 caracteres")
	@Email(message = "Email inválido")
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

	@NotEmpty(message = "Razão Social não pode ser vazia")
	@Length(min = 5, max = 200, message = "Razão Social deve conter entre 5 e 200 caracteres")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
		return "CadastroPjDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}
}
