package br.com.impulsotec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impulsotec.entity.Aluno;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
