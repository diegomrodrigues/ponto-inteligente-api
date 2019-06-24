package drsolutions.pontointeligente.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Classe de entrada na API Ponto Inteligente.<br/>
 * A classe PontoInteligenteApplication irá ler as configurações da classe passada como argumento e pronto, 
 * a sua aplicação estrá no ar.<br/>
 * A outra parte da interessante por conta da annotation @SpringBootApplication, ela é um Sterotype 
 * do Spring Boot que já encapsula algumas outras annotations, como a @EnableAutoConfiguration.<br/>
 * Essa última, por sua vez, carrega a AutoConfigurationPackages que é responsável por configurar 
 * os pacotes que devem ser escaneados, baseados na localização da classe.
 * 
 * @author Diego M. Rodrigues
 *
 */
@SpringBootApplication
@EnableCaching
public class PontoInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoInteligenteApplication.class, args);
	}

}
