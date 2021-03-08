package com.javatechie.aws.example;



import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javatechie.aws.example.Utils.Utils;
import com.javatechie.aws.example.cliente.Cliente;


@SpringBootApplication
@RestController
public class AwsElasticbeanstalkExampleApplication {

    @GetMapping("/")
    public String deploy() {
        return "Application deployed to AWS beanstalk....";
    }
	@GetMapping("/listarclientes")
	public List<Cliente> getData() {
		RestTemplate restTemplate = new RestTemplate();
		String answer =  restTemplate.getForObject(Utils.URL_DATABASE, String.class);
		List<Cliente> da = Utils.getClientes(answer);
		return da;
	}        
	@GetMapping("/kpideclientes")
	public String getKpis() throws FileNotFoundException, InterruptedException, ExecutionException {
		RestTemplate restTemplate = new RestTemplate();
		String answer =  restTemplate.getForObject(Utils.URL_DATABASE, String.class);
		String[] valores =  Utils.calcularDesviacion(Utils.getDataFromJSON(answer, "edad"));
		return Utils.getRespuestaKPIS(valores);
	}
	@PostMapping("/creacliente")
	public String saveEmployee(@RequestBody Cliente employee) {	
		RestTemplate restTemplate = new RestTemplate();
		String answer = restTemplate.postForObject(Utils.URL_DATABASE, employee, String.class);
		return Utils.getRespuestaCrearCliente(answer);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(AwsElasticbeanstalkExampleApplication.class, args);
    }

}
