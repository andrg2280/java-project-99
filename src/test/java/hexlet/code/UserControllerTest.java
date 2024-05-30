package hexlet.code;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
//import net.datafaker.Faker;
//import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*@Autowired
    private Faker faker;*/

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper om;


    @Test
    public void testIndex() throws Exception {
       // userRepository.save(testUser);
       var result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

       var body = result.getResponse().getContentAsString();
       assertThatJson(body).isArray();
    }
/*
    @Test
    public void testShow() throws Exception {
        userRepository.save(testUser);

        var request = get("/users/{id}", testUser.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("firstName").isEqualTo(testUser.getFirstName()),
                v -> v.node("lastName").isEqualTo(testUser.getLastName()),
                v -> v.node("email").isEqualTo(testUser.getEmail())
        );
    }

    @Test
    public void testIndexWithoutAuth() throws Exception {
        userRepository.save(testUser);
        var result = mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testCreate() throws Exception {
        var request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(testUser));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var user = userRepository.findByEmail(testUser.getEmail()).get();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getFirstName()).isEqualTo(testUser.getFirstName());
        Assertions.assertThat(user.getLastName()).isEqualTo(testUser.getLastName());
        Assertions.assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
        //Assertions.assertThat(user.getPasswordDigest()).isNotEqualTo(testUser.getPasswordDigest());
    }

    @Test
    public void testShowWithoutAuth() throws Exception {

        userRepository.save(testUser);

        var request = get("/users/{id}", testUser.getId());
        mockMvc.perform(request)
                .andExpect(status().isUnauthorized());
    }*/

}
