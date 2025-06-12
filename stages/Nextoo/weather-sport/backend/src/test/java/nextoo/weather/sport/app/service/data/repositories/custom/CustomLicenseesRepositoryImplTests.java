package nextoo.weather.sport.app.service.data.repositories.custom;

import nextoo.weather.sport.app.data.entities.*;
import nextoo.weather.sport.app.data.repositories.LicenseesRepository;
import nextoo.weather.sport.app.data.repositories.PersonsRepository;
import nextoo.weather.sport.app.data.repositories.SportsRepository;
import nextoo.weather.sport.app.models.WeatherType;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CustomLicenseesRepositoryImplTests {

    @Autowired
    private LicenseesRepository licenseesRepository;

    @Autowired
    private SportsRepository sportsRepository;

    @Autowired
    private PersonsRepository personsRepository;


    public void addLicensees(String firstName, String lastName, String sportName, int year) {
        PersonsEntity personsEntity = personsRepository.findIfExistByFirstNameAndLastNameIgnoreCase(firstName, lastName);
        SportsEntity sportsEntity = sportsRepository.findIfExistByNameIgnoreCase(sportName);

        LicenseesEntityId licenseesEntityId = new LicenseesEntityId();
        licenseesEntityId.setPersonId(personsEntity);
        licenseesEntityId.setSportId(sportsEntity);

        LicenseesEntity licenseesEntity = new LicenseesEntity();
        licenseesEntity.setId(licenseesEntityId);
        licenseesEntity.setPersonsEntity(personsEntity);
        licenseesEntity.setSportsEntity(sportsEntity);
        licenseesEntity.setStartDate(LocalDate.of(year, 1, 1));
        licenseesEntity.setEndDate(LocalDate.of(year, 3, 3));
        licenseesRepository.save(licenseesEntity);
    }


    public void addSport(String sportName, boolean indoor, boolean outdoor, WeatherType weatherType) {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName(sportName);
        sportsEntity.setIndoor(indoor);
        sportsEntity.setOutdoor(outdoor);
        PreferencesEntity preferencesEntity = new PreferencesEntity();
        preferencesEntity.setSportId(sportsEntity);
        preferencesEntity.setWeatherType(weatherType);
        sportsEntity.setPreferences(List.of(preferencesEntity));
        sportsRepository.save(sportsEntity);
    }


    public void addPerson(String firstName, String lastName) {
        PersonsEntity personsEntity = new PersonsEntity();
        personsEntity.setFirstName(firstName);
        personsEntity.setLastName(lastName);
        personsRepository.save(personsEntity);
    }

    @Test
    public void should_filter_by_licensees_active() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);

        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);


        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.of(false),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        Assertions.assertEquals(3, licenseesEntities.size());
        Assertions.assertEquals(2020, licenseesEntities.get(0).getStartDate().getYear());
        Assertions.assertEquals(2021, licenseesEntities.get(1).getStartDate().getYear());
        Assertions.assertEquals(2022, licenseesEntities.get(2).getStartDate().getYear());
    }


    @Test
    public void should_filter_by_sport() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.of(
                                "Rugby"
                        ),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        Assertions.assertEquals(1, licenseesEntities.size());
        Assertions.assertEquals("Rugby", licenseesEntities.get(0).getSportsEntity().getName());
    }


    @Test
    public void should_filter_by_isIndoor() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(
                                Boolean.TRUE
                        ),
                        Optional.empty(),
                        Optional.empty()
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        List<String> sportsNamesExpected = List.of("Football", "Handball");

        List<String> sportsNamesRes = licenseesEntities
                .stream()
                .map(LicenseesEntity::getSportsEntity)
                .map(SportsEntity::getName)
                .toList();

        Assertions.assertEquals(2, licenseesEntities.size());
        Assertions.assertTrue(sportsNamesRes.containsAll(sportsNamesExpected));
    }


    @Test
    public void should_filter_by_isOutdoor() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(
                                Boolean.TRUE
                        ),
                        Optional.empty()
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        List<String> sportsNamesExpected = List.of("Football", "Rugby");

        List<String> sportsNamesRes = licenseesEntities
                .stream()
                .map(LicenseesEntity::getSportsEntity)
                .map(SportsEntity::getName)
                .toList();

        Assertions.assertEquals(2, licenseesEntities.size());
        Assertions.assertTrue(sportsNamesRes.containsAll(sportsNamesExpected));
    }


    @Test
    public void should_filter_by_one_WeatherType() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(List.of(WeatherType.RAIN))
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        Assertions.assertEquals(1, licenseesEntities.size());
        Assertions.assertEquals("Handball", licenseesEntities.get(0).getSportsEntity().getName());
    }


    @Test
    public void should_filter_by_several_WeatherType() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);
        addLicensees("John", "Doe", "Ski", 2020);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(List.of(WeatherType.SNOW, WeatherType.RAIN))
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        List<String> sportsNamesExpected = List.of("Handball", "Ski");

        List<String> sportsNamesRes = licenseesEntities
                .stream()
                .map(LicenseesEntity::getSportsEntity)
                .map(SportsEntity::getName)
                .toList();

        Assertions.assertEquals(2, licenseesEntities.size());
        Assertions.assertTrue(sportsNamesRes.containsAll(sportsNamesExpected));
    }


    @Test
    public void should_filter_by_all_filters() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);


        addLicensees("John", "Doe", "Rugby", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2020);
        addLicensees("John", "Doe", "Rugby", 2020);
        addLicensees("Zinedine", "Zidane", "Football", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.of(false),
                        Optional.of("Handball"),
                        Optional.of(true),
                        Optional.of(false),
                        Optional.of(List.of(WeatherType.RAIN))
                );

        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        Assertions.assertEquals(1, licenseesEntities.size());
        Assertions.assertEquals("Handball", licenseesEntities.get(0).getSportsEntity().getName());
    }


    @Test
    public void should_return_all_licensees_without_filters() {
        addPerson("John", "Doe");
        addPerson("Léo", "Fantuz");
        addPerson("Zinedine", "Zidane");

        addSport("Football", true, true, WeatherType.SUN);
        addSport("Handball", true, false, WeatherType.RAIN);
        addSport("Rugby", false, true, WeatherType.SUN);
        addSport("Ski", false, true, WeatherType.SNOW);


        addLicensees("John", "Doe", "Football", 2020);
        addLicensees("Léo", "Fantuz", "Handball", 2021);
        addLicensees("Zinedine", "Zidane", "Rugby", 2022);

        LicenseesCriteriaWrapper licenseesCriteriaWrapper =
                new LicenseesCriteriaWrapper(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                );


        List<LicenseesEntity> licenseesEntities = licenseesRepository.findAllByCriteria(licenseesCriteriaWrapper);

        List<String> sportsNamesExpected = List.of("Football", "Handball", "Rugby");

        List<String> sportsNamesRes = licenseesEntities
                .stream()
                .map(LicenseesEntity::getSportsEntity)
                .map(SportsEntity::getName)
                .toList();

        Assertions.assertEquals(3, licenseesEntities.size());
        Assertions.assertTrue(sportsNamesRes.containsAll(sportsNamesExpected));
    }
}