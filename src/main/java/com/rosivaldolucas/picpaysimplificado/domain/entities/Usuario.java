package com.rosivaldolucas.picpaysimplificado.domain.entities;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "saldo")
  private Double saldo;

  @Enumerated(EnumType.STRING)
  @Column(insertable = false, updatable = false)
  private TipoUsuario tipo;

  protected Usuario() { }

  protected Usuario(final String nome, final String cpf, final Double saldo, final TipoUsuario tipo) {
    if (saldo < 0) throw new IllegalArgumentException("Saldo usuário não pode ser nulo.");

    this.nome = nome;
    this.cpf = cpf;
    this.saldo = saldo;
    this.tipo = tipo;
  }

  public boolean isValidoTransferir() {
    return this.tipo.equals(TipoUsuario.COMUM);
  }

  public void depositar(final Double valor) {
    this.saldo += valor;
  }

  public void retirar(final Double valor) {
    if (valor > this.saldo) throw new IllegalArgumentException("Usuário não tem saldo suficiente para retirar.");

    this.saldo -= valor;
  }

  public Double getSaldo() {
    return saldo;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

}
