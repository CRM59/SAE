package nextoo.weather.sport.app.controllers;

import nextoo.weather.sport.app.data.dto.LicenseesDTO;
import nextoo.weather.sport.app.exception.BadInputDTO;
import nextoo.weather.sport.app.exception.ConflictException;
import nextoo.weather.sport.app.models.Licensees;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;
import nextoo.weather.sport.app.service.LicenseesService;
import nextoo.weather.sport.app.validators.LicenseesValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/licensees")
public class LicenseesController {
    private final LicenseesService licenseesService;
    private final LicenseesValidator licenseesValidator;

    public LicenseesController(LicenseesService licenseesService, LicenseesValidator licenseesValidator) {
        this.licenseesService = licenseesService;
        this.licenseesValidator = licenseesValidator;
    }

    @PostMapping(path = "/add-licensees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Licensees> addNewLicensees(@RequestBody LicenseesDTO licenseesDTO) throws BadInputDTO {
        licenseesValidator.validate(licenseesDTO);

        try {
            Licensees newLicensees = this.licenseesService.addNewLicensees(
                    licenseesDTO.getSportName(),
                    licenseesDTO.getPersonFirstName(),
                    licenseesDTO.getPersonLastName(),
                    licenseesDTO.getStartDate(),
                    licenseesDTO.getEndDate()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(newLicensees);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        catch (ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


    @PutMapping(path = "/update-licensees")
    public ResponseEntity<Licensees> updateLicensees(@RequestBody LicenseesDTO licenseesDTO) throws IllegalArgumentException, BadInputDTO {
        licenseesValidator.validate(licenseesDTO);

        try {
            Licensees updatedLicensees = this.licenseesService.updateLicensees(
                    licenseesDTO.getSportName(),
                    licenseesDTO.getPersonFirstName(),
                    licenseesDTO.getPersonLastName(),
                    licenseesDTO.getStartDate(),
                    licenseesDTO.getEndDate()
            );

            return ResponseEntity.ok(updatedLicensees);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/criteria")
    public ResponseEntity<List<Licensees>> getAllByCriteria(LicenseesCriteriaWrapper criteria){
        return ResponseEntity.ok(licenseesService.getAllByCriteria(criteria));
    }
}
