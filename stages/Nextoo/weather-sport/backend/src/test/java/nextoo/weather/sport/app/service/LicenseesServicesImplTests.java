package nextoo.weather.sport.app.service;

import nextoo.weather.sport.app.data.entities.LicenseesEntity;
import nextoo.weather.sport.app.data.entities.LicenseesEntityId;
import nextoo.weather.sport.app.data.entities.PersonsEntity;
import nextoo.weather.sport.app.data.entities.SportsEntity;
import nextoo.weather.sport.app.data.repositories.LicenseesRepository;
import nextoo.weather.sport.app.data.repositories.PersonsRepository;
import nextoo.weather.sport.app.data.repositories.SportsRepository;
import nextoo.weather.sport.app.exception.ConflictException;
import nextoo.weather.sport.app.models.Licensees;
import nextoo.weather.sport.app.service.implementation.LicenseesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LicenseesServicesImplTests {

    @Mock
    private SportsRepository sportsRepository;
    @Mock
    private LicenseesRepository licenseesRepository;
    @Mock
    private PersonsRepository personsRepository;

    @InjectMocks
    private LicenseesServiceImpl pmlicensesService;

    @Test
    public void should_throw_IllegalArgumentException_when_sport_are_invalid() {
        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.any(String.class)
                        )
                )
                .thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pmlicensesService.addNewLicensees(
                    "xxx",
                    "Léo",
                    "Fantuz",
                    LocalDate.now(),
                    LocalDate.now()
            );
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pmlicensesService.updateLicensees(
                    "xxx",
                    "Léo",
                    "Fantuz",
                    LocalDate.now(),
                    LocalDate.now()
            );
        });
    }


    @Test
    public void should_throw_IllegalArgumentException_when_updating_licensees_with_nonexistent_person() {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Football");

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(sportsEntity);

        Mockito.when(
                        personsRepository.findIfExistByFirstNameAndLastNameIgnoreCase(
                                Mockito.anyString(),
                                Mockito.anyString()
                        )
                )
                .thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pmlicensesService.updateLicensees(
                    "xxx",
                    "Léo",
                    "Fantuz",
                    LocalDate.now(),
                    LocalDate.now()
            );
        });
    }


    @Test
    public void should_throw_IllegalArgumentException_when_updating_a_nonexistent_licensees() {
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setFirstName("Léo");
        personsEntity.setLastName("Fantuz");

        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Football");

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(sportsEntity);

        Mockito.when(
                        personsRepository.findIfExistByFirstNameAndLastNameIgnoreCase(
                                Mockito.anyString(),
                                Mockito.anyString()
                        )
                )
                .thenReturn(personsEntity);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            pmlicensesService.updateLicensees(
                    "xxx",
                    "Léo",
                    "Fantuz",
                    LocalDate.now(),
                    LocalDate.now()
            )
        );
    }


    @Test
    public void should_save_a_new_licensees() throws ConflictException {
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setFirstName("Léo");
        personsEntity.setLastName("Fantuz");

        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Football");

        LicenseesEntityId licenseesEntityId = new LicenseesEntityId();
        licenseesEntityId.setSportId(sportsEntity);
        licenseesEntityId.setPersonId(personsEntity);

        LicenseesEntity licenseesEntity = new LicenseesEntity();
        licenseesEntity.setId(licenseesEntityId);

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(sportsEntity);

        Mockito.when(
                        personsRepository.findIfExistByFirstNameAndLastNameIgnoreCase(
                                Mockito.anyString(),
                                Mockito.anyString()
                        )
                )
                .thenReturn(null);

        Mockito.when(
                        personsRepository.save(
                                Mockito.any(PersonsEntity.class)
                        )
                )
                .thenReturn(null);

        Mockito.when(
                        licenseesRepository.save(
                                Mockito.any(LicenseesEntity.class)
                        )
                )
                .thenReturn(licenseesEntity);

        Licensees res = pmlicensesService.addNewLicensees("Football",
                "Léo",
                "Fantuz",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 2));

        Assertions.assertEquals(personsEntity.getFirstName(), res.person().firstName());
        Assertions.assertEquals(personsEntity.getLastName(), res.person().lastName());
        Assertions.assertEquals(sportsEntity.getName(), res.sport());
        Assertions.assertDoesNotThrow(() -> IllegalArgumentException.class);
    }


    @Test
    public void should_update_a_licensees() {
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setFirstName("Léo");
        personsEntity.setLastName("Fantuz");

        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Football");

        LicenseesEntityId licenseesEntityId = new LicenseesEntityId();
        licenseesEntityId.setSportId(sportsEntity);
        licenseesEntityId.setPersonId(personsEntity);

        LicenseesEntity licenseesEntity = new LicenseesEntity();
        licenseesEntity.setId(licenseesEntityId);

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(sportsEntity);

        Mockito.when(
                        personsRepository.findIfExistByFirstNameAndLastNameIgnoreCase(
                                Mockito.anyString(),
                                Mockito.anyString()
                        )
                )
                .thenReturn(personsEntity);

        Mockito.when(
                        licenseesRepository.findById(
                                Mockito.any(LicenseesEntityId.class)
                        )
                )
                .thenReturn(Optional.of(licenseesEntity));


        Mockito.when(
                        licenseesRepository.save(
                                Mockito.any(LicenseesEntity.class)
                        )
                )
                .thenReturn(licenseesEntity);


        Licensees res = pmlicensesService.updateLicensees("Football",
                "Léo",
                "Fantuz",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 2));

        Assertions.assertEquals(2025, res.startDate().getYear());
        Assertions.assertEquals(1, res.startDate().getMonth().getValue());
        Assertions.assertEquals(1, res.startDate().getDayOfMonth());
        Assertions.assertDoesNotThrow(() -> IllegalArgumentException.class);
    }


}
