package br.com.impulsotec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impulsotec.entity.AlunoDisciplina;
import br.com.impulsotec.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {

	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
}
