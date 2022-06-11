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

import br.com.impulsotec.entity.Aluno;
import br.com.impulsotec.service.AlunoService;
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
public class AlunoResource {

	private AlunoService alunoService;
	
	@GetMapping(value="/alunos")
	@ApiOperation(value="Retorna uma lista de alunos")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Alunos encontrados com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<List<Aluno>> findAllAlunos(){
		List<Aluno> alunos = alunoService.findAllAlunos();
		return ResponseEntity.ok().body(alunos);
	}
	
	@GetMapping(value="/alunos/{id}")
	@ApiOperation(value="Busca um aluno pelo id")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Alunos encontrado com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Aluno> findById(@PathVariable Long id){
		Aluno aluno = alunoService.findById(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/alunos")
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Alunos cadastrado com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	@ApiOperation(value="Cadastra um aluno no banco")	
	public ResponseEntity<Void> insertAluno(@RequestBody Aluno objAluno) {
		Aluno aluno = alunoService.insertAluno(objAluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/alunos/{id}")
	@ApiOperation(value="Deleta um aluno pelo id")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Aluno deletado com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		alunoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/alunos/{id}")
	@ApiOperation(value="Atualiza um aluno pelo id")
	@ApiResponses(value= {
			@ApiResponse(code = 204, message = "Aluno alterado com sucesso!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Aconteceu gerada uma exceção")
	})
	public ResponseEntity<Void> updateAluno(@RequestBody Aluno objAluno, @PathVariable Long id){
		objAluno.setId(id);
		alunoService.updateAluno(objAluno);
		return ResponseEntity.noContent().build();
	}
	
	
}
