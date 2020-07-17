package com.exadel.booking.office.floor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class CustomizedFloorImpl implements CustomizedFloor<Floor> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Floor> findAllFloorsByOfficeId(UUID id) {
        return em.createQuery("from Floor where of_id=" + id, Floor.class)
                .getResultList();
    }
}
