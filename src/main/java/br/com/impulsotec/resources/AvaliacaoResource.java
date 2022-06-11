package br.com.impulsotec.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impulsotec.entity.Aluno;
import br.com.impulsotec.entity.AlunoDisciplina;
import br.com.impulsotec.entity.Avaliacao;
import br.com.impulsotec.entity.Disciplina;
import br.com.impulsotec.service.AvaliacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/api/v1")
@Api(value="API REST de controle de matriculas")
@CrossOrigin(origins="*")
@AllArgsConstructor
public class AvaliacaoResource {

	private AvaliacaoService avaliacaoService;
	
	@GetMapping(value="/avaliacoes")
	@ApiOperation(value="Retorna uma lista de avaliações")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Avaliações encontrados com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	private ResponseEntity<List<Avaliacao>> findAll(){
		List<Avaliacao> listAvaliacao = avaliacaoService.findAll();
		return ResponseEntity.ok().body(listAvaliacao);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/avaliacoes")
	@ApiOperation(value="Posta uma avaliação")
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Avaliação inserida com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Void> insertAvaliacao(@RequestBody Avaliacao objAvaliacao){
		objAvaliacao = avaliacaoService.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value="/{idAluno}/{idDisciplina}")
	public ResponseEntity<Avaliacao> findAvaliacaoForDisciplina(@PathVariable Long idAluno,@PathVariable Long idDisciplina){
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina(); 
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao avaliacao = avaliacaoService.findByNoteDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(avaliacao);
	}
	
	
	
	
}
