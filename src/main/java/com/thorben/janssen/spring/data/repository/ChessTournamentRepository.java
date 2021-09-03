package com.thorben.janssen.spring.data.repository;

import com.thorben.janssen.spring.data.model.ChessTournament;
import com.thorben.janssen.spring.data.model.TournamentIntf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessTournamentRepository extends JpaRepository<ChessTournament, Long> {
    
    TournamentIntf findByName(String name);
}
