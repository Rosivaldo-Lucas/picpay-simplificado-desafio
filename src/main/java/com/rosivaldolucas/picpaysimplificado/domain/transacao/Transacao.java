package com.rosivaldolucas.picpaysimplificado.domain.transacao;

import com.rosivaldolucas.picpaysimplificado.domain.usuario.Usuario;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACAO")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "VALOR")
  private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name = "ID_USUARIO_PAGADOR")
  private Usuario pagador;

  @ManyToOne
  @JoinColumn(name = "ID_USUARIO_RECEBEDOR")
  private Usuario recebedor;

  @CreatedDate
  @Column(name = "CRIADO_EM")
  private LocalDateTime criadoEm;

  protected Transacao() { }

  private Transacao(final Long id, final BigDecimal valor, final Usuario pagador, final Usuario recebedor) {
    this.id = id;
    this.valor = valor;
    this.pagador = pagador;
    this.recebedor = recebedor;
  }

  public static Transacao criarCom(final BigDecimal valor, final Usuario pagador, final Usuario recebedor) {
    return new Transacao(null, valor, pagador, recebedor);
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public Usuario getPagador() {
    return pagador;
  }

  public Usuario getRecebedor() {
    return recebedor;
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

}
