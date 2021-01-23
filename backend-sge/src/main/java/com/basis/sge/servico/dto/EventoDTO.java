package com.basis.sge.servico.dto;

<<<<<<< HEAD
=======
import com.basis.sge.dominio.EventoPergunta;
>>>>>>> 35068f16d29e356f54226ba74c0d564195fd47cd
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private Integer id;

    private String local;
    private String titulo;
    private String descricao;
    private Integer qtdVagas;
    private Double valor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Boolean tipoInscricao;
    private Integer idTipoEvento;
<<<<<<< HEAD
    private List<EventoPerguntaDTO> perguntas;
=======
    private List<EventoPergunta> perguntas;
>>>>>>> 35068f16d29e356f54226ba74c0d564195fd47cd
}
