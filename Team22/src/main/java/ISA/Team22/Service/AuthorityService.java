package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.Users.Authority;
import ISA.Team22.Repository.AuthorityRepository;
import ISA.Team22.Service.IService.IAuthorityService;

@Service
public class AuthorityService implements IAuthorityService{
	 @Autowired
	    private AuthorityRepository authorityRepository;

	    @Override
	    public Authority findById(Long id) {
	        Authority auth = this.authorityRepository.getOne(id);

	        return auth;
	    }

	    @Override
	    public Authority findByname(String name) {
	        Authority auth = this.authorityRepository.findByName(name);
	        return auth;
	    }
}
