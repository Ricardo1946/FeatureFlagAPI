package FeatureFlagAPI.ApiFlag.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI featureFlagOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FeatureFlag API")
                        .description("Backend para gestión de feature flags en entornos reales (dev, staging, prod). " +
                                "Permite activar/desactivar funcionalidades dinámicamente, habilitar pruebas A/B y gestionar releases por etapas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo 6")
                ));
    }
}
