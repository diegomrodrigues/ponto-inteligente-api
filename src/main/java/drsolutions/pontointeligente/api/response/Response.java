package drsolutions.pontointeligente.api.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Para padronizar o retorno das requisições de uma API Restful, o indicado é
 * a criação de uma​ ​classe​ ​responsável​ ​por​ ​encapsular​ ​os​ ​dados​
 * ​de​ ​retorno​ ​de​ ​um​ ​modo​ ​estruturado.<br/>
 * <br/>
 * Por isso devemos criar uma classe <strong>Response</strong>, que conterá uma
 * estrutura mínima para manipular os casos de sucesso ou erro em uma
 * requisição, mantendo assim toda a estrutura​ ​da​ ​API​ ​padronizada.
 * 
 * @author Diego M. Rodrigues
 *
 * @param <T>
 */
public class Response<T> {

	/* Irá conter os dados em caso de sucesso */
	private T data;
	
	/* Armazenar erros de sistemas */
	private List<String> errors;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
