package com.basis.sge.servico;

import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.TipoEventoRepositorio;
import com.basis.sge.servico.dto.TipoEventoDTO;
import com.basis.sge.servico.mapper.TipoEventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEventoServico {

    private final TipoEventoRepositorio tipoEventoRepositorio;
    private final TipoEventoMapper tipoEventoMapper;

    public List<TipoEventoDTO> listar() {
        List<TipoEvento> tipoEventoList = tipoEventoRepositorio.findAll();
        return tipoEventoMapper.toDto(tipoEventoList);
    }
}
