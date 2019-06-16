package drsolutions.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import drsolutions.pontointeligente.api.entities.Funcionario;
import drsolutions.pontointeligente.api.repositories.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

	@MockBean
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	private static final String CPF 	= "20047331803";
	private static final String EMAIL 	= "diego@drsolutions.com.br";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString()))
			.willReturn(new Funcionario());
		
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString()))
			.willReturn(new Funcionario());
		
		BDDMockito.given(this.funcionarioRepository.findByCpfOrEmail(Mockito.anyString(), Mockito.anyString()))
			.willReturn(new Funcionario());
		
		BDDMockito.given(this.funcionarioRepository.findOne(Mockito.anyLong()))
			.willReturn(new Funcionario());
		
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class)))
			.willReturn(new Funcionario());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf(CPF);
		
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail(EMAIL);
		
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpfOuEmail() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpfOuEmail(CPF, EMAIL);
		
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testBuscarFuncionarioPorId() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);
		
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testPersistirFuncionario() {
		Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());
		
		assertNotNull(funcionario);
	}
}
