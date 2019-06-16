package drsolutions.pontointeligente.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

	private static final String SENHA 	= "h64Rf0MTf1";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNula() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}
	
	@Test
	public void testGerarHashSenha() {
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		
		assertTrue(bCryptEncoder.matches(SENHA, hash));
	}
	
}
