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

import br.com.impulsotec.entity.Turma;
import br.com.impulsotec.service.TurmaService;
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
public class TurmaResource {

	private TurmaService turmaService;
	
	@GetMapping(value="/turmas")
	@ApiOperation(value="Retorna uma lista de turmas")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Turmas encontradas com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<List<Turma>> findAllTurma(){
		List<Turma> turma = turmaService.findAllTurma();
		return ResponseEntity.ok().body(turma);
	}
	
	@GetMapping(value="/turmas/{id}")
	@ApiOperation(value="Retorna uma única turma")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Turmas encontradas com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Turma> findbyId(@PathVariable Long id){
		Turma turma = turmaService.findById(id);
		return ResponseEntity.ok().body(turma);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/turmas")
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Alunos cadastrado com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	@ApiOperation(value="Cadastra uma turma no banco")
	public ResponseEntity<Void> insertTurma(@RequestBody Turma objTurma){
		Turma turma = turmaService.insertTurma(objTurma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turma.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/turmas/{id}")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Turma deletada com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	@ApiOperation(value="Deleta uma turma do banco")
	public ResponseEntity<Void> deleteTurmaById(@PathVariable Long id){
		turmaService.deleteTurma(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/turmas/{id}")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Turma deletada com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	@ApiOperation(value="Atualiza uma turma do banco")
	public ResponseEntity<Void> updateTurma(@RequestBody Turma objTurma, @PathVariable Long id){
		objTurma.setId(id);
		turmaService.updateTurma(objTurma);
		return ResponseEntity.noContent().build();
	}
	
	
}
