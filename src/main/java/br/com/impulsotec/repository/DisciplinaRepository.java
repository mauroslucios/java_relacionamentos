package br.com.impulsotec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impulsotec.entity.Disciplina;
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
