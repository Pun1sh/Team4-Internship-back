package com.exadel.booking.office.floor.room.place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class CustomizedPlaceImpl implements CustomizedPlace<Place> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Place> findAllPlacesByRoomId(UUID id) {
        return em.createQuery("from Place where rm_id=" + id, Place.class)
                .getResultList();
    }


}
