package nextoo.weather.sport.app.service;

import nextoo.weather.sport.app.exception.ConflictException;
import nextoo.weather.sport.app.models.Licensees;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface LicenseesService {
    Licensees addNewLicensees(String sportName, String personFirstName, String personLastName, LocalDate startDate, LocalDate endDate) throws ConflictException;

    Licensees updateLicensees(String sportName, String personFirstName, String personLastName, LocalDate startDate, LocalDate endDate);

    List<Licensees> getAllByCriteria(LicenseesCriteriaWrapper criteria);
}
