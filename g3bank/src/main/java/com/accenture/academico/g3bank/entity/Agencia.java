package com.accenture.academico.g3bank.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agencia implements Serializable {
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(nullable = false)
	 private String nomeAgencia;
	 private String enderecoAgencia;
	 private String telefoneAgencia;
	 private Integer numeroAgencia;
	 
	 @OneToMany(targetEntity=Conta.class,cascade = CascadeType.ALL, mappedBy = "agencia")
	 private List<Conta> contas;

	 public Agencia() {
	 }
	 
	 public Agencia(Long id, String nomeAgencia, String enderecoAgencia,
			String telefoneAgencia, Integer numeroAgencia,  List<Conta> contas) {
		this.id = id;
		this.nomeAgencia = nomeAgencia;
		this.enderecoAgencia = enderecoAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.numeroAgencia = numeroAgencia;
		this.contas = contas;
	}

	 public Long getId() {
		return id;
	}

	 public String getNomeAgencia() {
		return nomeAgencia;
	}

	 public String getEnderecoAgencia() {
		return enderecoAgencia;
	}

	 public void setEnderecoAgencia(String enderecoAgencia) {
		this.enderecoAgencia = enderecoAgencia;
	}

	 public String getTelefoneAgencia() {
		return telefoneAgencia;
	}

	 public void setTelefoneAgencia(String telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	 public Integer getNumeroAgencia() {
		return numeroAgencia;
	}
	
	 public List<Conta> getContas() {
		return contas;
	}
	 
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Agencia other = (Agencia) obj;
			if (id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!id.equals(other.id)) {
				return false;
			}
			return true;
		}

}
