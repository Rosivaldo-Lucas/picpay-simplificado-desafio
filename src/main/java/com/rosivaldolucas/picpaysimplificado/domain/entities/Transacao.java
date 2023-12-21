package com.rosivaldolucas.picpaysimplificado.domain.entities;

import com.rosivaldolucas.picpaysimplificado.domain.enums.StatusTransacao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transacao")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "codigo")
  private String codigo;

  @ManyToOne
  @JoinColumn(name = "id_usuario_pagador")
  private Usuario pagador;

  @ManyToOne
  @JoinColumn(name = "id_usuario_recebedor")
  private Usuario recebedor;

  @Column(name = "valor")
  private Double valor;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusTransacao status;

  protected Transacao() { }

  private Transacao(final Usuario pagador, final Usuario recebedor) {
    if (!pagador.isValidoTransferir()) throw new IllegalArgumentException("Usuário pagador não está habilitado para transferencias.");

    this.pagador = pagador;
    this.recebedor = recebedor;
    this.status = StatusTransacao.PENDENTE;
  }

  public static Transacao criar(final Usuario pagador, final Usuario recebedor) {
    return new Transacao(pagador, recebedor);
  }

  public void transferir(final Double valor) {
    this.pagador.retirar(valor);
    this.recebedor.depositar(valor);

    this.valor = valor;
    this.status = StatusTransacao.TRANSFERIDO;
    this.codigo = String.format("%d%08d", LocalDate.now().getYear(), 1);
  }

  public String obterCodigo() {
    return this.codigo;
  }

  public StatusTransacao obterStatus() {
    return this.status;
  }

}
