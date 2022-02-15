/*
@s da classe explicadas em phone.java

@Column(nullable = false, unique = true) = constraint também de unica


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
private List<Phone> phones;

- relacionamento: Uma pessoa muitos telefones
- é gerado uma tabela intermediária contendo o ID da Pessoa e o ID do Telefone
- fetch = FetchType.Lazy = performance de consulta para a junção de dados
- cascade = para cadastrar telefone através do cadastro de pessoa podendo ser inserir, alterar e remoção.

- Obs: não é criado um campo de lista telefones em Person, é criado apenas a tabela intermediária
PERSON_PHONES (
	PERSON_ID
	PHONES_ID
)

acesso através: peopleapi-live.herokuapp.com/api/v1/people

*/

package one.digitalinnovation.personapi.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	private LocalDate birthDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Phone> phones;

}
