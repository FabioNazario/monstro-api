package br.edu.infnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.model.Monstro;

@Repository
public interface MonstroRepository extends JpaRepository<Monstro,Long>{
	
}
