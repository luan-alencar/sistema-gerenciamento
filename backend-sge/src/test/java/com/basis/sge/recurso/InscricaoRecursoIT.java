package com.basis.sge.recurso;

import com.basis.sge.builder.InscricaoBuilder;
import com.basis.sge.util.IntTestComum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
public class InscricaoRecursoIT extends IntTestComum {

    private InscricaoBuilder inscricaoBuilder;

}
