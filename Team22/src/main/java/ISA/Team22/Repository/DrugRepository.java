package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import ISA.Team22.Domain.DrugManipulation.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
	Drug findByName(String name);
	Drug findByCode(String code);

}
