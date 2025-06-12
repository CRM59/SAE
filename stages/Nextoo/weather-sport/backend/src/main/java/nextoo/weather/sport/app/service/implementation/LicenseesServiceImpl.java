package nextoo.weather.sport.app.service.implementation;

import nextoo.weather.sport.app.data.entities.LicenseesEntity;
import nextoo.weather.sport.app.data.entities.LicenseesEntityId;
import nextoo.weather.sport.app.data.entities.PersonsEntity;
import nextoo.weather.sport.app.data.entities.SportsEntity;
import nextoo.weather.sport.app.data.repositories.LicenseesRepository;
import nextoo.weather.sport.app.data.repositories.PersonsRepository;
import nextoo.weather.sport.app.data.repositories.SportsRepository;
import nextoo.weather.sport.app.exception.ConflictException;
import nextoo.weather.sport.app.models.Licensees;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;
import nextoo.weather.sport.app.service.LicenseesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LicenseesServiceImpl implements LicenseesService {
    private final LicenseesRepository licenseesRepository;
    private final SportsRepository sportsRepository;
    private final PersonsRepository personsRepository;

    public LicenseesServiceImpl(LicenseesRepository licenseesRepository, SportsRepository sportsRepository, PersonsRepository personsRepository) {
        this.licenseesRepository = licenseesRepository;
        this.sportsRepository = sportsRepository;
        this.personsRepository = personsRepository;
    }

    public Licensees addNewLicensees(String sportName,
                                     String personFirstName,
                                     String personLastName,
                                     LocalDate startDate,
                                     LocalDate endDate) throws ConflictException {
        SportsEntity sportsEntity = sportsRepository.findIfExistByNameIgnoreCase(sportName);
        if (sportsEntity == null) {
            throw new IllegalArgumentException("Sports not found");
        }

        PersonsEntity personsEntity = personsRepository
                .findIfExistByFirstNameAndLastNameIgnoreCase(
                        personFirstName,
                        personLastName
                );

        if (personsEntity == null) {
            personsEntity = new PersonsEntity();
            personsEntity.setFirstName(personFirstName);
            personsEntity.setLastName(personLastName);
            //TODO configure on update cascade
            personsRepository.save(personsEntity);
        }

        LicenseesEntityId licenseesEntityId = new LicenseesEntityId();
        licenseesEntityId.setPersonId(personsEntity);
        licenseesEntityId.setSportId(sportsEntity);

        if(licenseesRepository.existsById(licenseesEntityId)) {
            throw new ConflictException();
        }

        LicenseesEntity licenseesEntity = new LicenseesEntity();
        licenseesEntity.setId(licenseesEntityId);
        licenseesEntity.setStartDate(startDate);
        licenseesEntity.setEndDate(endDate);

        return licenseesRepository.save(licenseesEntity).toLicensees();
    }


    public Licensees updateLicensees(String sportName,
                                     String personFirstName,
                                     String personLastName,
                                     LocalDate startDate,
                                     LocalDate endDate) {

        SportsEntity sportsEntity = sportsRepository.findIfExistByNameIgnoreCase(sportName);

        //TODO modifier exceptions
        if(sportsEntity == null) {
            throw new IllegalArgumentException("Sports not found");
        }

        PersonsEntity personsEntity = personsRepository
                .findIfExistByFirstNameAndLastNameIgnoreCase(
                        personFirstName,
                        personLastName
                );

        if(personsEntity == null) {
            throw new IllegalArgumentException("person not found");
        }

        LicenseesEntityId licenseesEntityId = new LicenseesEntityId();
        licenseesEntityId.setPersonId(personsEntity);
        licenseesEntityId.setSportId(sportsEntity);

        //TODO bonus: faire une recherche par sous attributs pour une seule requête
        Optional<LicenseesEntity> licenseesEntity = licenseesRepository.findById(licenseesEntityId);

        if(licenseesEntity.isEmpty()) {
            throw new IllegalArgumentException("licensees not found");
        }

        licenseesEntity.orElseThrow().setStartDate(startDate);
        licenseesEntity.orElseThrow().setEndDate(endDate);

        return licenseesRepository.save(licenseesEntity.orElseThrow()).toLicensees();
    }


    public List<Licensees> getAllByCriteria(LicenseesCriteriaWrapper criteria) {
        return licenseesRepository.findAllByCriteria(criteria).stream()
                .map(LicenseesEntity::toLicensees)
                .toList();
    }
}
