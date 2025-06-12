package nextoo.weather.sport.app.data.repositories.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import nextoo.weather.sport.app.data.entities.LicenseesEntity;
import nextoo.weather.sport.app.data.repositories.CustomLicenseesRepository;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomLicenseesRepositoryImpl implements CustomLicenseesRepository {
    private final EntityManager entityManager;

    public CustomLicenseesRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LicenseesEntity> findAllByCriteria(LicenseesCriteriaWrapper criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LicenseesEntity> criteriaQuery = cb.createQuery(LicenseesEntity.class);
        Root<LicenseesEntity> root = criteriaQuery.from(LicenseesEntity.class);
        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        criteria.active().ifPresent(active -> {
            if (criteria.active().get()) {
                predicates.add(
                        cb.and(
                                cb.lessThan(root.get("startDate"), LocalDate.now()),
                                cb.greaterThan(root.get("endDate"), LocalDate.now())
                        )
                );
            } else {
                predicates.add(
                        cb.or(
                                cb.greaterThan(root.get("startDate"), LocalDate.now()),
                                cb.lessThan(root.get("endDate"), LocalDate.now())
                        )
                );
            }
        });


        if (
                criteria.sportName().isPresent()
                        && !criteria.sportName().get().isEmpty()
        ) {
            predicates.add(
                    cb.equal(
                            cb.upper(root.get("id").get("sportId").get("name")),
                            criteria.sportName().get().toUpperCase()
                    )
            );
        }


        criteria.isIndoor().ifPresent(indoor -> predicates.add(
                cb.equal(
                        root.get("id").get("sportId").get("indoor"),
                        indoor
                )
        ));

        criteria.isOutdoor().ifPresent(outdoor -> predicates.add(
                cb.equal(
                        root.get("id").get("sportId").get("outdoor"),
                        outdoor
                )
        ));

        criteria.weathersTypes().ifPresent(weathersType -> predicates.add(
                root.get("id").get("sportId").get("preferences").get("weatherType").in(weathersType)
        ));


        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[]{})));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
