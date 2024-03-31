package com.rosivaldolucas.picpaysimplificado.domain.usuario;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "USUARIO")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "DOCUMENTO")
  private String documento;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "SENHA")
  private String senha;

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_USUARIO")
  private TipoUsuario tipoUsuario;

  @OneToOne
  @JoinColumn(name = "ID_CARTEIRA")
  private Carteira carteira;

  public void podeRealizarPagamento() {
    if (this.tipoUsuario.equals(TipoUsuario.LOGISTA)) {
      throw new IllegalArgumentException("");
    }
  }

  public void debitar(final BigDecimal valor) {
    this.carteira.validarDebito(valor);
    this.carteira.debitar(valor);
  }

  public void creditar(final BigDecimal valor) {
    this.carteira.creditar(valor);
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDocumento() {
    return documento;
  }

  public String getEmail() {
    return email;
  }
}
