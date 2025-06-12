package nextoo.validation.movies_app.services.implementations;

import nextoo.validation.movies_app.data.dto.ProfileDTO;
import nextoo.validation.movies_app.data.entities.*;
import nextoo.validation.movies_app.data.entities.mappers.ProfileMapper;
import nextoo.validation.movies_app.data.repositories.ProfileRepository;
import nextoo.validation.movies_app.modele.Profile;
import nextoo.validation.movies_app.services.ProfileService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }


    public List<Profile> getAllProfiles() {

        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toProfile)
                .toList();
    }

    public Profile addProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setName(profileDTO.getName());
        profileEntity.setTag(profileDTO.getTag());
        profileDTO.getLang().ifPresent(profileEntity::setLang);
        profileDTO.getAdult().ifPresent(profileEntity::setAdult);

        profileDTO.getGenres().ifPresent(genres ->
            profileEntity.setGenres(
                    genres.stream()
                            .flatMap(
                                    genre -> generateGenres(
                                            profileEntity,
                                            genre
                                    ).stream()
                            )
                            .collect(Collectors.toSet())
            )
        );


        return profileMapper.toProfile(profileRepository.save(profileEntity));
    }


    public Set<GenreEntity> generateGenres(ProfileEntity profileEntity, int gid) {
        Set<GenreEntity> genres = new HashSet<>();

        GenreEntity genreEntity = new GenreEntity();
        GenreEntityId genreEntityId = new GenreEntityId();

        genreEntityId.setPid(profileEntity);
        genreEntityId.setGid(gid);



        genreEntity.setId(genreEntityId);
        genres.add(genreEntity);


        return genres;
    }
}
