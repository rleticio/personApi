/*
1 funcionalidade = criação de pessoas

1 coisa a fazer é criar as classes que servem de "dawn",
implementa data acess object,
classe que gerencia todo o banco de dados.
O spring extrai isso através da classe Spring Data JPA,

- Chamamos de Repository a classe que gerencia o banco de dados.

- interface que extende JpaRepository
- através de generics passamos a ENTIDADE que queremos gerenciar e o
tipo de dados do ID, que é LONG.
		 --

public interface PersonRepository extends JpaRepository<Person, Long> {	

}

- Com o Spring não precisa nem abrir e nem fechar banco de dados
- apenas extendendo essa interface possui as operações básicas de banco de dados.

@Repository: cria beans para a parte de persistência, além de capturar exceções específicas de persistência.
	     persistir dado é garantir que um dado foi salvo e que poderá ser recuperado quando necessário no futuro.
@Component: marca os beans como componentes gerenciados do Spring
@EnableJpaRepositories: to use JPA repositories, we have to indicate it to Spring.


*/



package one.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.personapi.entity.Person;

@Repository
@Component
@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person, Long> {	

}
