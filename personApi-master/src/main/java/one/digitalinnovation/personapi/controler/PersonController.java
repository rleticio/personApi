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

// ------------------------------------------------------------------------------------------------

/*
EXPLICAÇÃO 2

O controller ter que servir apenas para ponto de entrada, sem regras de negócio
Criar service para fazer o papel de salvar e interação com repository

atalho intellij - shift option command l - atalho reformata arquivos, otimizando imports e apagando imports desnecessários

@ResponseStatus(value = HttpStatus.CREATED) = código 201

*/

// ----------------------------------------------------------------------------

/*
EXPLICAÇÃO 3

@valid = chamar objeto de outro lugar para ele fazer as validações dele, DTO.

Só pode salvar person, para converter personDTO para person:
Person personToSave = Person.builder()
		            	.firstname(personDTO.getFirstName())
				.lastname(personDTO.getLastName())
				.birthDate(personDRO.getBirthDate()) //dto data de nascimento como String, porém banco localdate
							criar objeto dateformat
				.phones(personDTO.getPhones()) também teria que fazer toda conversão
				.build();

para evitar isso, vamos utilizar a biblioteca MapStruck
ajuda converter dto para entidade e entidade para dto.	

ACESSAR mapstruct.org 

editar pom.xml

embaixo do lombok adicionar:

<path>
	<groupid>org.mapstruct</groupId>
	<artifactId>masstruct-processor</artifactId>
	<version>1.3.1.Final</version.
</path>


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
@AllArgsConstructor(onConstructor = @_ _(@Autowired)
public class PersonController {

	private PersonService personService;


	/*
	removido construtor padrao devido a anotação @AllArgsConstructor(onConstructor = @_ _(@Autowired)
	
	@Autowired(required = true)
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	*/

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}


	//Listar todos
	@GetMapping
	public List<PersonDTO> listall() {
		return personService.listAll();
	}
	

	// como tem 2 gets, precisa criar "/{id}"
	// @pathvariable = 
	/*
		Exemplo:
		@GetMapping("/api/employees/{id}")
		@ResponseBody
		public String getEmployeesById(@PathVariable String id) {
    			return "ID: " + id;
		}

	the @PathVariable annotation can be used to handle template variables in the request
	URI mapping, and set them as method parameters.

	*/



	@GetMapping("/{id}")
	public PersonDTO findById(@pathvariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@pathvariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}


	@PutMapping("/{id}")	
	public MessageResponseDTO updateById(@PathVariableLong id, @RequestBody @valid PersonDTO personDTO) throws PersonNotFoundException{
		return personService.updateById(id, personDTO);
	}




}
