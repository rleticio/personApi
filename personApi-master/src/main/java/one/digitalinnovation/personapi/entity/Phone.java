/*
Lombok:
@Data = Getters e Setters
@AllArgsConstructor = construtor com os campos
@NoArgsConstructor = construtor sem o campo
@Builder = cria a classe builder para instanciar o objeto usando a classe criada, ex:
Cliente cliente = Cliente.builder()                                          
                         .nome("Titulo Cliente")
                         .cnpj("1234567890")
                         .endereco("Endereço Cliente")
                         .build();


Anotações JPA para fazer o mapeamento das entidades e criar os modelos de dados, Tabelas..
Obrigação 1: @Entity = indica que a classe é uma entidade

@id = chave primaria
@GeneratedValue(strategy = GenerationType.IDENTITY) = banco de dados gera a chave primaria

@Enumerated(EnumType.STRING) = usar enum e o tipo de dado é String

@Column(nullable = false) = constraint que não pode ser nulo

*/




package one.digitalinnovation.personapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PhoneType type;
	
	@Column(nullable = false)
	private String number;

}
