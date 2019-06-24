package drsolutions.pontointeligente.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO para o cadastro de Lançamento na API<br/>
 * <br/>
 * As <strong>regras de validação</strong> estão nessa classe, com anotações como:<br/>
 * @NotEmpty<br/>
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
public class LancamentoDto {
	private Optional<Long> id = Optional.empty();
	private String data;
	private String tipo;
	private String descricao;
	private String localizacao;
	private Long funcionarioId;

	public LancamentoDto() {
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotEmpty(message = "Data não pode ser vazia.")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	@Override
	public String toString() {
		return "LancamentoDto [id=" + id + ", data=" + data + ", tipo=" + tipo + ", descricao=" + descricao
				+ ", localizacao=" + localizacao + ", funcionarioId=" + funcionarioId + "]";
	}
}
