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

}
