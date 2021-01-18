package com.basis.sge.servico;

import com.basis.sge.dominio.TipoEvento;
import com.basis.sge.repositorio.TipoEventoRepositorio;
import com.basis.sge.servico.dto.TipoEventoDTO;
import com.basis.sge.servico.mapper.TipoEventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEventoServico {


    private final TipoEventoRepositorio tipoEventoRepositorio;
    private final TipoEventoMapper tipoEventoMapper;

    @RequestMapping(name = "/api/eventos/tipos")
    public List<TipoEventoDTO> listar() {
        List<TipoEvento> tipoEventoList = tipoEventoRepositorio.findAll();
        return tipoEventoMapper.toDto(tipoEventoList);
    }

    public TipoEventoDTO obterPreInscricaoPorId(Integer id) {
        TipoEvento tipoEvento = tipoEventoRepositorio.findById(id).get();
        return tipoEventoMapper.toDto(tipoEvento);
    }

    public void deletar(Integer id) {
        tipoEventoRepositorio.deleteById(id);
    }

    public TipoEventoDTO atualizar(TipoEventoDTO tipoEventoDTO) {
        TipoEvento tipoEventoAtualizado = tipoEventoMapper.toEntity(tipoEventoDTO);
        tipoEventoRepositorio.save(tipoEventoAtualizado);
        return tipoEventoMapper.toDto(tipoEventoAtualizado);
    }

    @RequestMapping(name = "/api/eventos/tipos")
    public TipoEventoDTO salvar(TipoEventoDTO tipoEventoDTO) {
        TipoEvento tipoEvento = tipoEventoMapper.toEntity(tipoEventoDTO);
        tipoEventoRepositorio.save(tipoEvento);
        return tipoEventoMapper.toDto(tipoEvento);
    }
}
