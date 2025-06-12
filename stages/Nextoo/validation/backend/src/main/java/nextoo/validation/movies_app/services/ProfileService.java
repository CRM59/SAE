package nextoo.validation.movies_app.services;

import nextoo.validation.movies_app.data.dto.ProfileDTO;
import nextoo.validation.movies_app.modele.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    List<Profile> getAllProfiles();

    Profile addProfile(ProfileDTO profileDTO);
}
