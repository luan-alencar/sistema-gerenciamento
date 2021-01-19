package com.basis.sge.configuracao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.mail")
@Getter
@Setter
public class ApplicationProperties {

    private String enderecoRemetente;

    private String nomeRemetente;

}
