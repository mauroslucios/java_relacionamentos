package br.com.impulsotec.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_aluno")
public class Aluno  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, message = "Nome deve conter no mínimo 3 caracteres")
	@Size(max = 30, message = "Nome deve conter no máximo 30 caracteres")
	private String nome;
	
	@NotBlank
	@Size(max = 11, message = "Cpf deve conter no mínimo 11 caracteres")
	@Size(min = 11, message = "Cpf deve conter no máximo 11 caracteres")
	@Column(unique=true)
	private String cpf;
	
	@NotBlank
	@Email
	@Column(unique=true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="turma")
	@JsonIgnore
	private Turma turma;
	
	
	@ManyToMany
	@JoinTable(name="aluno_disciplina",
	joinColumns= {
		@JoinColumn(name="disciplina_id", referencedColumnName="id", nullable = false)},
	inverseJoinColumns= {
		@JoinColumn(name="aluno_id", referencedColumnName="id", nullable = false)}
	)
	private List<Disciplina> disciplinas;
		
	public Aluno() {
		
	}
	
	
	
	
	
}


