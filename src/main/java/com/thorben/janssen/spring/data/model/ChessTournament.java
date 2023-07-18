package com.thorben.janssen.spring.data.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@Cacheable
public class ChessTournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_seq")
    @SequenceGenerator(name = "tournament_seq", sequenceName = "tournament_sequence", initialValue = 100)
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Version
    private int version;

    @ManyToMany
    // private List<ChessPlayer> players = new ArrayList<>();
    private Set<ChessPlayer> players = new HashSet<>();

    @OneToMany
    private Set<ChessGame> games = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getVersion() {
        return version;
    }

    public Set<ChessPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<ChessPlayer> players) {
        this.players = players;
    }

    // public List<ChessPlayer> getPlayers() {
    //     return players;
    // }

    // public void setPlayers(List<ChessPlayer> players) {
    //     this.players = players;
    // }

    public Set<ChessGame> getGames() {
        return games;
    }

    public void setGames(Set<ChessGame> games) {
        this.games = games;
    }
}