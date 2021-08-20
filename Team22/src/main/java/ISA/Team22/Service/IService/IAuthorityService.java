package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.Users.Authority;


public interface IAuthorityService {
	 Authority findById(Long id);
	    Authority findByname(String name);

}
