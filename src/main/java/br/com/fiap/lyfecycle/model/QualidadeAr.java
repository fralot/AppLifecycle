package br.com.fiap.lyfecycle.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "t_qualidade_ar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QualidadeAr {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "qualidade_ar_seq")
    @SequenceGenerator(
            name = "qualidade_ar_seq",
            sequenceName = "qualidade_ar_seq",
            allocationSize = 1)

    @Column(name = "id_dado")
    private Long id;

    @Column(name = "id_estacao")
    private Long idEstacao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "nivel_pm2_5")
    private Double nivelPm25;

    @Column(name = "nivel_pm10")
    private Double nivelPm10;

    @Column(name = "config_alertas_id_configuracao")
    private Long configAlertasIdConfiguracao;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEstacao() {
        return idEstacao;
    }

    public void setIdEstacao(Long idEstacao) {
        this.idEstacao = idEstacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getNivelPm25() {
        return nivelPm25;
    }

    public void setNivelPm25(Double nivelPm25) {
        this.nivelPm25 = nivelPm25;
    }

    public Double getNivelPm10() {
        return nivelPm10;
    }

    public void setNivelPm10(Double nivelPm10) {
        this.nivelPm10 = nivelPm10;
    }

    public Long getConfigAlertasIdConfiguracao() {
        return configAlertasIdConfiguracao;
    }

    public void setConfigAlertasIdConfiguracao(Long configAlertasIdConfiguracao) {
        this.configAlertasIdConfiguracao = configAlertasIdConfiguracao;
    }
}
