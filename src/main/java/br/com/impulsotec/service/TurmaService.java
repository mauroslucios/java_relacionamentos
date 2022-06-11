package br.com.impulsotec.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.impulsotec.entity.Turma;
import br.com.impulsotec.repository.TurmaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurmaService {

	TurmaRepository turmaRepository;
	
	public List<Turma> findAllTurma(){
		return turmaRepository.findAll();
	}
	
	public Turma findById(Long id) throws ObjectNotFoundException{
		Optional<Turma> turma = turmaRepository.findById(id);
		return turma.orElseThrow(()-> new ObjectNotFoundException(null, "Turma não encontrada!"));
	}
	
	public Turma insertTurma(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public void deleteTurma(Long id) {
		turmaRepository.deleteById(id);
	}
	
	public Turma updateTurma(Turma objTurma) {
		Turma turma = findById(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return insertTurma(turma);
	}
}
