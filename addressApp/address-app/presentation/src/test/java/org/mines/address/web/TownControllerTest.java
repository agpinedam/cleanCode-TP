package org.mines.address.web;

import org.junit.jupiter.api.Test;
import org.mines.address.domain.model.Town;
import org.mines.address.port.driving.TownUseCase;
import org.mines.address.web.config.WebTestConfig;
import org.mines.address.web.controller.TownController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TownController.class)
@AutoConfigureMockMvc
@Import(WebTestConfig.class)
public class TownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TownUseCase townUseCase;

    @Test
    void shouldGetATown() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(townUseCase.get(uuid)).thenReturn(Optional.of(
                Town.TownBuilder.aTown().withId(uuid).withName("some town").build()
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/town/%s".formatted(uuid.toString()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("some town"));

    }

    @Test
    void shouldNotGetInstanceData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/town/%s".formatted(UUID.randomUUID().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void shouldCreateATown() throws Exception {

        UUID id = UUID.randomUUID();
        when(townUseCase.save(any())).thenReturn(Town.TownBuilder.aTown()
                .withId(id)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/town").content("{\"name\":\"truc\", \"postCode\":1}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id.toString()));

        verify(townUseCase).save(any());

    }
}
