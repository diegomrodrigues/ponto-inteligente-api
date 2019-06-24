package drsolutions.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import drsolutions.pontointeligente.api.dtos.CadastroPjDto;
import drsolutions.pontointeligente.api.entities.Empresa;
import drsolutions.pontointeligente.api.entities.Funcionario;
import drsolutions.pontointeligente.api.enums.PerfilEnum;
import drsolutions.pontointeligente.api.response.Response;
import drsolutions.pontointeligente.api.services.EmpresaService;
import drsolutions.pontointeligente.api.services.FuncionarioService;
import drsolutions.pontointeligente.api.utils.PasswordUtils;

/**
 * Criar um Controller para o Cadastro de PJ.<br/>
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
 * Anotações<br/>
 * 
 * @RestController - Define a classe como um
 *                 Controller @RequestMapping("/api/cadastrar-pj") - Mapeamento
 *                 de URL
 * @CrossOrigin(origins = "*") - Esse controller pode aceitar conexões de
 *                      qualquer domínio <br/>
 * <br/>
 * Requisição: POST - Postman<br/>
 * http://localhost:8080/api/cadastrar-pj<br/>
 * JSON<br/>
 * {
 *	"nome": "Diego Rodrigues",
 *	"email": "diego@drsolutions.com.br",
 *	"senha": "123456",
 *	"cpf": "19737032039",
 *	"razaoSocial": "drSolutions Tecnologia",
 *	"cnpj": "11861136000102"
 * }<br/>
 * <br/>
 * Retorno: 200<br/>
 * JSON<br/>
 * {
 *   "data": {
 *       "id": 1,
 *       "nome": "Diego Rodrigues",
 *       "email": "diego@drsolutions.com.br",
 *       "senha": null,
 *       "cpf": "19737032039",
 *       "razaoSocial": "drSolutions Tecnologia",
 *       "cnpj": "11861136000102"
 *   },
 *   "errors": []
 * }<br/>
 *  
 * @author Diego M. Rodrigues
 *
 */
@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPjController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPjController.class);

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EmpresaService empresaService;

	public CadastroPjController() {
	}

	/**
	 * Cadastra uma pessoa jurídica no sistema
	 * 
	 * @param CadastroPjDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPjDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPjDto>> cadastrar(@Valid @RequestBody CadastroPjDto CadastroPjDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando PJ: {}", CadastroPjDto.toString());

		Response<CadastroPjDto> response = new Response<CadastroPjDto>();

		validarDadosExistentes(CadastroPjDto, result);
		Empresa empresa = this.converterDtoParaEmpresa(CadastroPjDto);
		Funcionario funcionario = this.converterDtoParaFuncionario(CadastroPjDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			
			/* Retorno: Código de estado HTTP 400 - Requisição inválida */
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistir(funcionario);

		response.setData(this.converterCadastroPjDto(funcionario));
		
		/* Retorno: Código de estado HTTP 200 - Sucesso */
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se a empresa ou funcionário já existem na base de dados
	 * 
	 * @param CadastroPjDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPjDto CadastroPjDto, BindingResult result) {
		this.empresaService.buscarPorCnpj(CadastroPjDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcionarioService.buscarPorCpf(CadastroPjDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(CadastroPjDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "E-mail já existente.")));
	}

	/**
	 * Converte os dados do DTO para empresa
	 * 
	 * @param CadastroPjDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroPjDto CadastroPjDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(CadastroPjDto.getCnpj());
		empresa.setRazaoSocial(CadastroPjDto.getRazaoSocial());

		return empresa;
	}

	/**
	 * Converte os dados do DTO para funcionário<br/>
	 * O Funcionário cadastrado possuirá o perfil ROLE_ADMIN
	 * 
	 * @param CadastroPjDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(CadastroPjDto CadastroPjDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(CadastroPjDto.getNome());
		funcionario.setEmail(CadastroPjDto.getEmail());
		funcionario.setCpf(CadastroPjDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(CadastroPjDto.getSenha()));

		return funcionario;
	}

	/**
	 * Popula o DTO de cadastro com os dados do funcionário e empresa
	 * 
	 * @param funcionario
	 * @return CadastroPjDto
	 */
	private CadastroPjDto converterCadastroPjDto(Funcionario funcionario) {
		CadastroPjDto CadastroPjDto = new CadastroPjDto();
		CadastroPjDto.setId(funcionario.getId());
		CadastroPjDto.setNome(funcionario.getNome());
		CadastroPjDto.setEmail(funcionario.getEmail());
		CadastroPjDto.setCpf(funcionario.getCpf());
		CadastroPjDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		CadastroPjDto.setCnpj(funcionario.getEmpresa().getCnpj());

		return CadastroPjDto;
	}
}
