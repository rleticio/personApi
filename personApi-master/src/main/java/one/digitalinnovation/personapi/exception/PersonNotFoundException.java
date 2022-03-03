package one.digitalinnovation.personapi.exception;

//extends Throwable
//Super() para chamar o construtor da classe Exception
// @ResponseStatus(HttpsStatus.NOT_FOUND) retorna o erro 404



@ResponseStatus(HttpsStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {

	public PersonNotFoundException(Long id) {
		super("Person not found with id " + id);	
	}
	
}