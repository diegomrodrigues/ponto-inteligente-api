package drsolutions.pontointeligente.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

import drsolutions.pontointeligente.api.dtos.CadastroPfDto;
import drsolutions.pontointeligente.api.entities.Empresa;
import drsolutions.pontointeligente.api.entities.Funcionario;
import drsolutions.pontointeligente.api.enums.PerfilEnum;
import drsolutions.pontointeligente.api.response.Response;
import drsolutions.pontointeligente.api.services.EmpresaService;
import drsolutions.pontointeligente.api.services.FuncionarioService;
import drsolutions.pontointeligente.api.utils.PasswordUtils;

/**
 * Criar um Controller para o Cadastro de PF.<br/>
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
 *                 Controller @RequestMapping("/api/cadastrar-pf") - Mapeamento
 *                 de URL
 * @CrossOrigin(origins = "*") - Esse controller pode aceitar conexões de
 *                      qualquer domínio <br/>
 * <br/>
 * Requisição: POST - Postman<br/>
 * http://localhost:8080/api/cadastrar-pf<br/>
 * JSON<br/>
 * {
 *	"nome": "Natalia Neau",
 *	"email": "natalia@drsolutions.com.br",
 *	"senha": "123456",
 *	"cpf": "72471428045",
 *	"cnpj": "11861136000102"
 * }<br/>
 * <br/>
 * Retorno: 200<br/>
 * JSON<br/>
 * {
 *   "data": {
 *       "id": 4,
 *       "nome": "Natalia Neau",
 *       "email": "natalia@drsolutions.com.br",
 *       "senha": null,
 *       "cpf": "72471428045",
 *       "valorHora": {
 *           "present": false
 *       },
 *       "qtdHorasTrabalhadasDia": {
 *           "present": false
 *       },
 *       "qtdHorasAlmoco": {
 *           "present": false
 *       },
 *       "cnpj": "11861136000102"
 *   },
 *   "errors": []
 * }<br/>
 * <br/>
 * Requisição: POST - Postman<br/>
 * http://localhost:8080/api/cadastrar-pf<br/>
 * JSON<br/>
 * {
 *	"nome": "Adriana Oliveira",
 *	"email": "adriana@casadosaber.com.br",
 *	"senha": "987654",
 *	"cpf": "12831339030",
 *	"valorHora": "50",
 *	"cnpj": "73234282000165"
 * }<br/>
 * Retorno: 200<br/>
 * JSON<br/>
 * {
 *   "data": {
 *       "id": 6,
 *       "nome": "Adriana Oliveira",
 *       "email": "adriana@casadosaber.com.br",
 *       "senha": null,
 *       "cpf": "12831339030",
 *       "valorHora": "50",
 *       "qtdHorasTrabalhadasDia": null,
 *       "qtdHorasAlmoco": null,
 *       "cnpj": "73234282000165"
 *   },
 *   "errors": []
 * } <br/>
 *  
 * @author Diego M. Rodrigues
 *
 */
@RestController
@RequestMapping("api/cadastrar-pf")
@CrossOrigin(origins = "*")
public class CadastroPfController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPfController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public CadastroPfController() {
	}
	
	/**
	 * Cadastra um funcionário (pessoa física) no sistema
	 * 
	 * @param CadastroPfDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPfDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPfDto>> cadsatrar(@Valid @RequestBody CadastroPfDto CadastroPfDto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando PF: {}", CadastroPfDto.toString());
		
		Response<CadastroPfDto> response = new Response<CadastroPfDto>();
		
		validarDadosExistentes(CadastroPfDto, result);
		Funcionario funcionario = this.converterDtoParaFuncionario(CadastroPfDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PF: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(CadastroPfDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPfDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se a empresa está cadastrada na base de dados e se o funcionário não existe na base de dados
	 * 
	 * @param CadastroPfDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPfDto CadastroPfDto, BindingResult result) {
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(CadastroPfDto.getCnpj()); 
		if (!empresa.isPresent()) {
				result.addError(new ObjectError("empresa", "Empresa não cadastrada"));
		}
				
		this.funcionarioService.buscarPorCpf(CadastroPfDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(CadastroPfDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "E-mail já existente.")));
	}

	/**
	 * Converte os dados do DTO para funcionário<br/>
	 * O Funcionário cadastrado possuirá o perfil ROLE_USUARIO
	 * 
	 * @param CadastroPfDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(CadastroPfDto CadastroPfDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(CadastroPfDto.getNome());
		funcionario.setEmail(CadastroPfDto.getEmail());
		funcionario.setCpf(CadastroPfDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(CadastroPfDto.getSenha()));
		
		CadastroPfDto.getQtdHorasAlmoco()
			.ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		
		CadastroPfDto.getQtdHorasTrabalhadasDia()
			.ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
		CadastroPfDto.getValorHora()
		
			.ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));

		return funcionario;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do funcionário
	 * 
	 * @param funcionario
	 * @return CadastroPfDto
	 */
	private CadastroPfDto converterCadastroPfDto(Funcionario funcionario) {
		CadastroPfDto CadastroPfDto = new CadastroPfDto();
		CadastroPfDto.setId(funcionario.getId());
		CadastroPfDto.setNome(funcionario.getNome());
		CadastroPfDto.setEmail(funcionario.getEmail());
		CadastroPfDto.setCpf(funcionario.getCpf());
		CadastroPfDto.setCnpj(funcionario.getEmpresa().getCnpj());
		
		funcionario.getQtdHorasAlmocoOpt()
				.ifPresent(qtdHorasAlmoco -> CadastroPfDto
				.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		
		funcionario.getQtdHorasTrabalhoDiaOpt()
				.ifPresent(qtdHorasTrabDia -> CadastroPfDto.setQtdHorasTrabalhadasDia(Optional.of(Float.toString(qtdHorasTrabDia))));
		
		funcionario.getValorHoraOpt()
				.ifPresent(valorHora -> CadastroPfDto.setValorHora(Optional.of(valorHora.toString())));

		return CadastroPfDto;
	}
}
