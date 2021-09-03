package com.thorben.janssen.spring.data.repository;

import java.util.List;

import com.thorben.janssen.spring.data.model.BetterPlayerFullNameIntf;
import com.thorben.janssen.spring.data.model.ChessPlayer;
import com.thorben.janssen.spring.data.model.PlayerFullNameIntf;
import com.thorben.janssen.spring.data.model.PlayerName;
import com.thorben.janssen.spring.data.model.PlayerNameIntf;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChessPlayerRepository extends JpaRepository<ChessPlayer, Long> {

    // @Query("SELECT p FROM ChessPlayer p LEFT JOIN FETCH p.tournaments")
    @EntityGraph(attributePaths = "tournaments")
    List<ChessPlayer> findWithTournamentsBy();

    PlayerName findDtoByFirstName(String firstName);

    PlayerNameIntf findByFirstName(String firstName);

    @Query(value = "SELECT first_name as firstName, last_name as lastName FROM Chess_Player p WHERE p.first_name=:firstName", 
           nativeQuery = true)
    PlayerNameIntf findNativeByFirstName(String firstName);

    @Query(value = "SELECT first_name as firstName, last_name as lastName FROM Chess_Player p WHERE p.first_name=:firstName", 
           nativeQuery = true)
    PlayerName findDtoNativeByFirstName(String firstName);

    PlayerFullNameIntf findFullNameByFirstName(String firstName);

    BetterPlayerFullNameIntf findBetterFullNameByFirstName(String firstName);
}