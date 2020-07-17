package com.exadel.booking.office.floor.room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class CustomizedRoomImpl implements CustomizedRoom<Room> {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Room> findAllRoomsByFloorId(UUID id) {
        return em.createQuery("from Room where fl_id=" + id, Room.class)
                .getResultList();
    }
}
