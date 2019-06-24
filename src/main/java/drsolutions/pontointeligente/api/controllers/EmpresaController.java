package drsolutions.pontointeligente.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drsolutions.pontointeligente.api.dtos.EmpresaDto;
import drsolutions.pontointeligente.api.entities.Empresa;
import drsolutions.pontointeligente.api.response.Response;
import drsolutions.pontointeligente.api.services.EmpresaService;

/**
 * Criar um Controller para a consulta de Empresas pelo CNPJ.<br/>
 * <br/>
 * O Spring Rest possui a anotação <strong>Controller</strong>, que uma vez
 * adicionada a uma classe Java, aceitará um ‘path’ como parâmetro, tornando
 * esse componente disponível para acesso HTTP​ ​para​ ​o​ ​‘path’​
 * ​adicionado.<br/>
 * <br/>
 * Com os controllers, também é possível gerenciar os verbos HTTP (GET, POST,
 * PUT, DELETE,...) para cada método da classe, permitindo criar todos os
 * acessos Restful para a ​API.<br/>
 * <br/>
 * 
 * @RestController - Define a classe como um
 *                 Controller @RequestMapping("/api/cadastrar-pj") - Mapeamento
 *                 de URL
 * @CrossOrigin(origins = "*") - Esse controller pode aceitar conexões de
 *                      qualquer domínio <br/>
 * <br/>
 * Requisição: GET - Postman<br/>
 * http://localhost:8080/api/empresas/cnpj/73234282000165<br/>
 * <br/>
 * Retorno: 200<br/>
 * JSON<br/>
 * {
 *   "data": {
 *       "id": 2,
 *       "razaoSocial": "Colégio Casa do Saber",
 *       "cnpj": "73234282000165"
 *   },
 *   "errors": []
 * }<br/>
 *  
 * @author Diego M. Rodrigues
 *
 */
@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaService empresaService;

	public EmpresaController() {
	}

	/**
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula um DTO com os dados de uma empresa.
	 * 
	 * @param empresa
	 * @return EmpresaDto
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());

		return empresaDto;
	}

}
