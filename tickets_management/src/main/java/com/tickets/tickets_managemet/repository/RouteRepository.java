package com.tickets.tickets_managemet.repository;

import com.tickets.tickets_managemet.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
