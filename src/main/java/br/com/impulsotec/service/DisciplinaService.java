package br.com.impulsotec.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.impulsotec.entity.Disciplina;
import br.com.impulsotec.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DisciplinaService {

	private DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> findAll(){
		return disciplinaRepository.findAll();
	}
	
	public Disciplina findById(Long id) throws ObjectNotFoundException {
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return disciplina.orElseThrow(()-> new ObjectNotFoundException(null, "Disciplina não encontrada!"));
	}
	
	public Disciplina insertDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
		
	public void deleteDisciplina(Long id) {
		disciplinaRepository.deleteById(id);
	}
	
	public Disciplina updateDisciplina(Disciplina objDisciplina) {
		Disciplina disciplina = findById(objDisciplina.getId());
		disciplina.setNome(objDisciplina.getNome());
		return insertDisciplina(disciplina);
	}
	
}
