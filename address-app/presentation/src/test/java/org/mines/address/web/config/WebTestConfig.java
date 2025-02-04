package org.mines.address.web.config;

import org.mines.address.port.driving.AddressUseCase;
import org.mines.address.port.driving.TownUseCase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebTestConfig {
    @MockBean
    private TownUseCase townUseCase;

    @MockBean
    private AddressUseCase addressUseCase;
}
