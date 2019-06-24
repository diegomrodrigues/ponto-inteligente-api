package drsolutions.pontointeligente.api.dtos;

/**
 * DTO para o cadastro de uma Empresa na API<br/>
 * Teremos o cadastro de Empresa.<br/>
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
public class EmpresaDto {
	private Long id;
	private String razaoSocial;
	private String cnpj;

	public EmpresaDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + "]";
	}
}
