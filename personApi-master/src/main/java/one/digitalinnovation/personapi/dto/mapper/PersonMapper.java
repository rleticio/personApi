/*
@Mapper(componentModel = "spring") = para o MapStruck saber que é uma classe que ele vai processar

dto para objeto de banco de dados, usar toModel (convenção de nomes)
Person toModel(PersonDTO personDTO);    (retorna Person e recebe PersonDTO); // apenas com o nome toModel faz a conversão.

entidade para dto, uso toDTO
PersonDTO toDTO(Person person); (retorna PersonDTO e recebe person);


como no dto data está string e na entidade localdate,
instrui pro mapstruck fazer conversão
@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")



*/

package one.digitalinnovation.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(PersonDTO personDTO);
	
	PersonDTO toDTO(Person person);
	

}
