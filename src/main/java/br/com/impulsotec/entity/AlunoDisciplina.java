package br.com.impulsotec.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AlunoDisciplina implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Disciplina disciplina;
}
