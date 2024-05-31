package hexlet.code.component;
import hexlet.code.dto.LabelCreateDto;
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
import io.sentry.Sentry;


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
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
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
