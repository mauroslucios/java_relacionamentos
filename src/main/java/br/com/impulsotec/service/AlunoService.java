package br.com.impulsotec.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.impulsotec.entity.Aluno;
import br.com.impulsotec.repository.AlunoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

	private AlunoRepository alunoRepository;
	
	public List<Aluno> findAllAlunos(){
		return alunoRepository.findAll();
	}
	
	public Aluno findById(Long id) throws ObjectNotFoundException {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.orElseThrow(()-> new ObjectNotFoundException(null, "Aluno não encontrado!"));
	}
	
	public Aluno insertAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public void deleteById(Long id) {
		alunoRepository.deleteById(id);
	}
	
	public Aluno updateAluno(Aluno objAluno) {
		Aluno aluno = findById(objAluno.getId());
		aluno.setNome(objAluno.getNome());
		aluno.setCpf(objAluno.getCpf());
		aluno.setEmail(objAluno.getEmail());
		aluno.setTurma(objAluno.getTurma());
		aluno.setDisciplinas(objAluno.getDisciplinas());
		return insertAluno(aluno);
	}
}
