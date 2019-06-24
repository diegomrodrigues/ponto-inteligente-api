package drsolutions.pontointeligente.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO para o cadastro de Funcionário na API<br/>
 * <br/>
 * As <strong>regras de validação</strong> estão nessa classe, com anotações como:<br/>
 * @NotEmpty<br/>
 * @Length<br/>
 * @Email<br/>
 * @CPF<br/>
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
public class FuncionarioDto {
	private Long id;
	private String nome;
	private String email;
	private Optional<String> senha = Optional.empty();
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();

	public FuncionarioDto() {
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
	@Email(message="E-mail inválido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	public void setQtdHorasTrabalhoDia(Optional<String> qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco=" + qtdHorasAlmoco
				+ "]";
	}
	
}
