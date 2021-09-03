package com.thorben.janssen.spring.data.controller;

import java.util.Optional;

import javax.persistence.NoResultException;

import com.thorben.janssen.spring.data.model.ChessPlayer;
import com.thorben.janssen.spring.data.repository.ChessPlayerRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "player")
@Transactional
public class ChessPlayerController {
    
    private ChessPlayerRepository playerRepo;

    public ChessPlayerController(ChessPlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    @GetMapping(path = "/{id}")
    public ChessPlayer getPlayerById(@PathVariable("id") Long id) {
        Optional<ChessPlayer> a = playerRepo.findById(id);
        if (!a.isPresent()) {
            throw new NoResultException();
        }
        return a.get();
    }
}
