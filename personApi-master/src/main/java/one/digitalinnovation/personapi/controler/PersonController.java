/*
EXPLICAÇÃO 1

@RestController (ponto de entrada do projeto, indicando api rest)
@RequestMapping("/api/v1/people") (caminho de acesso principal a nossa API)
@GetMapping (operação HTTP do tipo get, Toda requisição de pagina acessa através de uma operação GET, get = obter dado)

@GetMapping
public String getBook() {
	return "API Test!";
}

@PostMapping = criação

1 - Injeção de dependendia da classe PersonRepository
1.1 - cria a variável private PersonRepository personRepository;
1.2 - injeta através do construtor com a anotação @Autowired(required = true)
- vantagens da injeção no construtor e que facilita a criação de testes unitários.

@Autowired
public PersonController(PersonRepository personRepository) {
	this.personRepository = personRepository;
}

@PostMapping
public String createPerson(@RequestBody Person person) {
	personRepository.save(person);
	
}

- json do postman que informa o objeto person com os atributos
- @RequestBody informa que esse objeto esta vindo de uma requisição HTTP do tipo pessoas

exemplo json:
{
	"firstname":"Rodrigo",
	"lastName":"Peleias",
	"cpf":"369.333.878-79"
	"phones": [
		{
			"type": MOBILE",
			"number": "(11)999999999"
		}
	]
}

DTO = data transfer object
objeto do DTO = MessageResponseDTO

retorna o dto com metodo que retorna mansagem String ao inves de String direto sem DTO:

@PostMapping
public MessageResponseDTO createPerson(@RequestBody Person person) {
	Person savedPerson = personRepository.save(person);
	return MessageResponseDTO
		.builder()
		.message("Created person with ID " + savedPerson.getId())
		.build()
	
}

*/

/*
EXPLICAÇÃO 2

*/





package one.digitalinnovation.personapi.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	private PersonService personService;

	@Autowired(required = true)
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

}
