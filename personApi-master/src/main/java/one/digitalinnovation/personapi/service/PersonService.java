/*
@Service = Indica para o Spring para ele gerenciar uma classe responsável por colocar todas as regras de negócio, um serviço.



*/



package one.digitalinnovation.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;

@Service
@AllArgsConstructor(onConstructor = @_ _(@Autowired)
public class PersonService {

	private PersonRepository personRepository;	
	
	//private final PersonMapper personMapper = PersonMapper.INSTANCE;
	private PersonMapper personMapper;	

	/*
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	*/
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {

		Person personToSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO.builder().message("Created Person with ID " + savedPerson.getId()).build();

	}

	
	public List<Person.DTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());

	}

	/*
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if (optionalPerson.isEmpty()) {
			throw new PersonNotFoundException(id);
		}
		return personMapper.toDTO(optionalPerson.get());
	}

	*/

	/*
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		
		Person person = personRepository.findById(id)
			.orElseThrow(() -> new PersonNotFoundException(id));
		
		return personMapper.toDTO(person);
	}
	*/

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		
		Person person = verifyIfExists(id);
		
		return personMapper.toDTO(person);
	}

	
	public void delete(long id) throws PersonNotFoundException {
		
		verifyIfExists(id);

		personRepository.deleteById(id);

	}


	/*
	public MessageResponseDTO updateById(Long id, PersonDTO personDRO) throws PersonNotFoundException {
	
		verifyIfExists(id);

		Person personToUpdate = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToUpdate);
		return MessageResponseDTO
				.builder()
				.message("Update Person with ID " + savedPerson.getId())
				.build();

	}
	*/


	public MessageResponseDTO updateById(Long id, PersonDTO personDRO) throws PersonNotFoundException {
	
		verifyIfExists(id);

		Person personToUpdate = personMapper.toModel(personDTO);

		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Update person with ID");

	}


// os códigos repetidos foram criados métodos PRIVADOS para não repetir os códigos.

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		
		return personRepository.findById(id)
		.orElseThrow(() -> new PersonNotFoundException(id));
	
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message){
		return MessageResponseDTO
			.builder()
			.message(message + id)
			.build();
	}



}
