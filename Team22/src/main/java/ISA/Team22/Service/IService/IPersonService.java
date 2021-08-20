package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.Person;

public interface IPersonService {
	 Person findById(Long id);
	 Person  findByEmail(String email);
	 List<Person > findAll ();
	 Person  save(PersonRequestDTO personRequest);
	 Person savePerson(Person person);
	 void changePasswordFirstLogin(String oldPassword, String newPassword);
}
