package com.basis.sge.servico;

import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.TipoEventoRepositorio;
import com.basis.sge.servico.dto.TipoEventoDTO;
import com.basis.sge.servico.exception.RegraNegocioException;
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

    public TipoEventoDTO obterTipoEventoPorId(Integer id) {
        TipoEvento tipoEvento = tipoEventoRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Id não encontrado!"));
        return tipoEventoMapper.toDto(tipoEvento);
    }

}
