package nextoo.validation.movies_app.services.implementations;

import nextoo.validation.movies_app.data.dto.ProfileDTO;
import nextoo.validation.movies_app.data.entities.GenreEntity;
import nextoo.validation.movies_app.data.entities.GenreEntityId;
import nextoo.validation.movies_app.data.entities.ProfileEntity;
import nextoo.validation.movies_app.data.entities.mappers.GenreMapper;
import nextoo.validation.movies_app.data.entities.mappers.ProfileMapper;
import nextoo.validation.movies_app.data.entities.mappers.WatchedMapper;
import nextoo.validation.movies_app.data.repositories.GenreRepository;
import nextoo.validation.movies_app.data.repositories.ProfileRepository;
import nextoo.validation.movies_app.modele.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTests {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GenreRepository genreRepository;

    private ProfileMapper profileMapper;

    private ProfileServiceImpl profileServiceImpl;

    @BeforeEach
    void setUp() {
        profileMapper = new ProfileMapper(new GenreMapper(), new WatchedMapper());
        profileServiceImpl = new ProfileServiceImpl(profileRepository, profileMapper);
    }


    public ProfileEntity addProfils(String name, String lang, boolean adult) {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setName(name);
        profileEntity.setLang(lang);
        profileEntity.setAdult(adult);


        return profileRepository.save(profileEntity);
    }


    public void initGenres(ProfileEntity profileEntity, int gid) {

        GenreEntity genreEntity = new GenreEntity();
        GenreEntityId genreEntityId = new GenreEntityId();

        genreEntityId.setPid(profileEntity);
        genreEntityId.setGid(gid);


        genreEntity.setId(genreEntityId);


        genreRepository.save(genreEntity);
    }

    @Test
    void should_return_all_profils() {
        initGenres(addProfils("Leo", "fr", true), 1);
        initGenres(addProfils("titi", "en", false), 2);
        initGenres(addProfils("tata", "de", true), 3);

        List<Profile> profiles = profileServiceImpl.getAllProfiles();

        Assertions.assertEquals(3, profiles.size());
        Assertions.assertEquals("Leo", profiles.get(0).name());
        Assertions.assertEquals("titi", profiles.get(1).name());
        Assertions.assertEquals("tata", profiles.get(2).name());
    }


    @Test
    void should_add_new_profil() {
        ProfileDTO profileDTO = new ProfileDTO("Leo", "1234", Optional.of("fr"), Optional.of(true), Optional.of(List.of(15, 12)));

        profileServiceImpl.addProfile(profileDTO);

        List<Profile> profiles = profileServiceImpl.getAllProfiles();

        Assertions.assertEquals(1, profiles.size());
        Assertions.assertEquals("Leo", profiles.get(0).name());
    }

    @Test
    void should_add_new_profil_without_language() {
        ProfileDTO profileDTO = new ProfileDTO("Leo", "1234", Optional.empty(), Optional.of(true), Optional.of(List.of(15, 12)));

        profileServiceImpl.addProfile(profileDTO);

        List<Profile> profiles = profileServiceImpl.getAllProfiles();

        Assertions.assertEquals(1, profiles.size());
        Assertions.assertNull(profiles.get(0).lang());
    }

    @Test
    void should_add_new_profil_without_genres() {
        ProfileDTO profileDTO = new ProfileDTO("Leo", "1234", Optional.of("fr"), Optional.of(true), Optional.empty());

        profileServiceImpl.addProfile(profileDTO);

        List<Profile> profiles = profileServiceImpl.getAllProfiles();

        Assertions.assertEquals(1, profiles.size());
        Assertions.assertTrue(profiles.get(0).genres().isEmpty());
    }


    @Test
    void should_add_new_profil_without_language_and_genres() {
        ProfileDTO profileDTO = new ProfileDTO("Leo", "1234", Optional.empty(), Optional.of(true), Optional.empty());

        profileServiceImpl.addProfile(profileDTO);

        List<Profile> profiles = profileServiceImpl.getAllProfiles();

        Assertions.assertEquals(1, profiles.size());
        Assertions.assertNull(profiles.get(0).lang());
        Assertions.assertTrue(profiles.get(0).genres().isEmpty());
    }
}
