package hexlet.code.component;
import hexlet.code.dto.LabelCreateDto;
import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;
import hexlet.code.model.User;


import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.CustomUserDetailsService;
import hexlet.code.service.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {


    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    @Autowired
    private final CustomUserDetailsService userService;
    private final LabelService labelService;

    private PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findByEmail("hexlet@example.com").isEmpty()) {
            var email = "hexlet@example.com";
            var userData = new User();
            userData.setEmail(email);
            userData.setPassword("qwerty");
            userService.createUser(userData);
        }
        if (labelRepository.findByName("bug").isEmpty()) {
            var name = "bug";
            var labelData = new LabelCreateDto();
            labelData.setName(name);
            labelService.create(labelData);
        }
        if (labelRepository.findByName("feature").isEmpty()) {
            var name = "feature";
            var labelData = new LabelCreateDto();
            labelData.setName(name);
            labelService.create(labelData);
        }



    }

}
