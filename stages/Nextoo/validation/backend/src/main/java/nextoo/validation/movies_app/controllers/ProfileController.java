package nextoo.validation.movies_app.controllers;

import nextoo.validation.movies_app.data.dto.ProfileDTO;
import nextoo.validation.movies_app.exceptions.BadInputDTO;
import nextoo.validation.movies_app.modele.Profile;
import nextoo.validation.movies_app.services.ProfileService;
import nextoo.validation.movies_app.validators.ProfileValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/profiles")
public class ProfileController {
    private final ProfileValidator profileValidator;
    private final ProfileService profileService;

    public ProfileController(ProfileValidator profileValidator, ProfileService profileService) {
        this.profileValidator = profileValidator;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.getAllProfiles());
    }


    @PostMapping(path="/add")
    public ResponseEntity<Profile> addProfile(@RequestBody ProfileDTO profileDTO) throws BadInputDTO {
        profileValidator.validate(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.addProfile(profileDTO));
    }
}
