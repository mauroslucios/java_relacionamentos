package br.com.impulsotec.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.impulsotec.entity.Disciplina;
import br.com.impulsotec.service.DisciplinaService;
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
public class DisciplinaResource {

	private DisciplinaService disciplinaService;
	
	
	@GetMapping(value="/disciplinas")
	@ApiOperation(value="Retorna uma lista de disciplinas")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Disciplinas encontradas com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<List<Disciplina>> findAllDisciplina(){
		List<Disciplina> disciplina = disciplinaService.findAll();
		return ResponseEntity.ok().body(disciplina);
	}
	
	@GetMapping(value="/disciplinas/{id}")
	@ApiOperation(value="Retorna uma única disciplina")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Disciplinas encontradas com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Disciplina> findById(@PathVariable Long id) {
		Disciplina disciplina = disciplinaService.findById(id);
		return ResponseEntity.ok().body(disciplina);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/disciplinas")	
	@ApiOperation(value="Adiciona uma disciplina")
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Disciplina criada com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Disciplina> insertDisciplina(@RequestBody Disciplina objDisciplina){
		Disciplina disciplina = disciplinaService.insertDisciplina(objDisciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disciplina.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/disciplinas/{id}")
	@ApiOperation(value="Deleta uma única disciplina")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Disciplina deletada com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id){
		disciplinaService.deleteDisciplina(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping(value="/disciplinas/{id}")
	@ApiOperation(value="Atualiza uma única disciplina")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Disciplina alterada com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Void> updateTurma(@RequestBody Disciplina objDisciplina, @PathVariable Long id){
		objDisciplina.setId(id);
		disciplinaService.updateDisciplina(objDisciplina);
		return ResponseEntity.noContent().build();
	}
	
	
}
