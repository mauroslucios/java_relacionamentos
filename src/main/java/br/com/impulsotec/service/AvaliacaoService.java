package br.com.impulsotec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.impulsotec.entity.AlunoDisciplina;
import br.com.impulsotec.entity.Avaliacao;
import br.com.impulsotec.repository.AvaliacaoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvaliacaoService {

	AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao save(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}
	
	public List<Avaliacao> findAll(){
		return avaliacaoRepository.findAll();
	}
	
	public Avaliacao findByNoteDisciplina(AlunoDisciplina alunoDisciplina) {
		return avaliacaoRepository.findByAlunoDisciplina(alunoDisciplina);
	}
}
