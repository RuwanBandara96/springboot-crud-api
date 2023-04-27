package com.springboot.camel.postgresql.repository;

import com.springboot.camel.postgresql.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findByEventId(int eventId);

}
