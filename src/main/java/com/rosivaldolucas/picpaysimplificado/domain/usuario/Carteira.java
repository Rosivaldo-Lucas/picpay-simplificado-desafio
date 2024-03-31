package com.rosivaldolucas.picpaysimplificado.domain.usuario;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "CARTEIRA")
public class Carteira {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "SALDO")
  private BigDecimal saldo;

  public BigDecimal getSaldo() {
    return this.saldo;
  }

  public void validarDebito(final BigDecimal valor) {
    if (!(this.saldo.compareTo(valor) >= 0)) {
      throw new IllegalArgumentException("");
    }
  }

  public void debitar(final BigDecimal valor) {
    this.validarDebito(valor);

    this.saldo = this.saldo.subtract(valor);
  }

  public void creditar(final BigDecimal valor) {
    this.saldo = this.saldo.add(valor);
  }

}
