package se.lexicon.spring_boot_rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.spring_boot_rest.Model.dto.RoleDto;


@SpringBootTest
@AutoConfigureMockMvc

@Transactional
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        String requestBodyAdmin = "{ \"name\" : \"ADMIN\" }"; // creating role ->

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/role/").content(requestBodyAdmin).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("ADMIN"));

        String requestBodyUser = "{ \"name\" : \"USER\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/role/").content(requestBodyUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("USER"));


        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());

    }

    @Test
    public void test_findAll() throws Exception {
/*
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/role/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("USER"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("ADMIN"));

 */


    }

    @Test
    public void test_createRole() throws Exception {
      /*  String requestBodyGuest = "{ \"name\" : \"GUEST\" }";

        MvcResult mvcResult = mockMvc.
                perform(MockMvcRequestBuilders.
                        post("/api/v1/role/")
                        .content(requestBodyGuest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        int actualStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 201;

        Assertions.assertEquals(expectedStatusCode, actualStatusCode);

        String responseJSONBody = mvcResult.getResponse().getContentAsString();

        RoleDto actualRoleDto = objectMapper.readValue(responseJSONBody, RoleDto.class);
        RoleDto expectedRoleDto = new RoleDto(3, "GUEST");

        Assertions.assertEquals(expectedRoleDto.getName(), actualRoleDto.getName());

        Assertions.assertEquals(expectedRoleDto, actualRoleDto); // ?

       */


    }
}

