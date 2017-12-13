package co.edu.poligran.Example.Example.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.poligran.Example.Example.Entity.NumberEntity;

@Transactional
public interface NumberRepository extends JpaRepository<NumberEntity, Integer> {
	@Query("select u from NumberEntity u where u.number=:number")
	NumberEntity findByNumber(@Param("number") String number);
}
