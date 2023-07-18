package com.thorben.janssen.spring.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import com.thorben.janssen.spring.data.controller.ChessPlayerController;
import com.thorben.janssen.spring.data.model.BetterPlayerFullNameIntf;
import com.thorben.janssen.spring.data.model.ChessPlayer;
import com.thorben.janssen.spring.data.model.ChessTournament;
import com.thorben.janssen.spring.data.model.PlayerFullNameIntf;
import com.thorben.janssen.spring.data.model.PlayerName;
import com.thorben.janssen.spring.data.model.PlayerNameIntf;
import com.thorben.janssen.spring.data.model.TournamentIntf;
import com.thorben.janssen.spring.data.repository.ChessPlayerRepository;
import com.thorben.janssen.spring.data.repository.ChessTournamentRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class TestDemo {

    private static final Logger log = LoggerFactory.getLogger(TestDemo.class);

    @Autowired
    private ChessPlayerRepository playerRepo;

    @Autowired
    private ChessTournamentRepository tournamentRepo;

    @Autowired
    private ChessPlayerController playerController;

    @Test
    @Transactional
    public void findAll() {
        log.info("... findAll ...");

        List<ChessPlayer> players = playerRepo.findAll();
        assertThat(players.size()).isEqualTo(19);
        
        players.forEach(player -> log.info(player.getFirstName() + " " 
                                            + player.getLastName()+ " played in "
                                            + player.getTournaments().size()+ " tournaments.")); 
    }



    @Test
    @Transactional
    public void findWithTournaments() {
        log.info("... findWithTournaments ...");

        List<ChessPlayer> players = playerRepo.findWithTournamentsBy();
        assertThat(players.size()).isEqualTo(19);
        
        players.forEach(player -> log.info(player.getFirstName() + " " 
                                            + player.getLastName() + " played in "
                                            + player.getTournaments().size() + " tournaments.")); 
    }



    @Test
    @Transactional
    @Commit
    public void removePlayerFromTournament() {
        log.info("... removePlayerFromTournament ...");

        ChessTournament tournament = tournamentRepo.getById(2L);
        ChessPlayer removedPlayer = playerRepo.getById(34L);
        
        tournament.getPlayers().remove(removedPlayer);
    }



    @Test
    public void getPlayerNamesDto() {
        log.info("... getPlayerNamesDto ...");

        PlayerName player = playerRepo.findDtoByFirstName("Magnus");
        log.info("PlayerName: " + player.toString());
        log.info(player.getFirstName()+" "+player.getLastName());
    }

    @Test
    public void getPlayerNames() {
        log.info("... getPlayerNames ...");

        PlayerNameIntf player = playerRepo.findByFirstName("Magnus");
        log.info("PlayerNameIntf: " + player.toString());
        log.info(player.getFirstName()+" "+player.getLastName());
    }

    @Test
    public void getPlayerNamesDtoNative() {
        log.info("... getPlayerNamesDtoNative ...");

        try {
            PlayerNameIntf player = playerRepo.findDtoNativeByFirstName("Magnus");
            log.info(player.getFirstName()+" "+player.getLastName());
        } catch (ConverterNotFoundException e) {
            log.error(e.toString());
        }
    }

    @Test
    @Transactional
    public void getTournamentWithPlayers() {
        log.info("... getTournamentWithPlayers ...");

        TournamentIntf tournament = tournamentRepo.findByName("Tata Steel Chess Tournament 2021");

        log.info("======== Test Output ===========");
        log.info(tournament.getPlayers().size() + " players played in the "+tournament.getName()+" tournament.");
    }

    @Test
    public void getPlayerFullNames() {
        log.info("... getPlayerFullNames ...");

        PlayerFullNameIntf player = playerRepo.findFullNameByFirstName("Magnus");
        log.info(player.getFullName());
    }

    @Test
    public void getBetterPlayerFullNames() {
        log.info("... getBetterPlayerFullNames ...");

        BetterPlayerFullNameIntf player = playerRepo.findBetterFullNameByFirstName("Magnus");
        log.info(player.getFullName());
    }



    @Test
    public void getPlayerUsingCache() {
        log.info("... getPlayerUsingCache ...");

        playerController.getPlayerById(1L);
        playerController.getPlayerById(1L);
    }







    
    
    
    @Test
    public void getPlayerNamesNative() {
        log.info("... getPlayerNamesNative ...");

        PlayerNameIntf player = playerRepo.findNativeByFirstName("Magnus");
        log.info(player.getFirstName()+" "+player.getLastName());
    }

    @Test
    @Transactional
    public void readOnlyTransaction() {
        log.info("... readOnlyTransaction ...");

        Long start = System.currentTimeMillis();
        for (int i=0; i<100; i++) {
            ChessPlayer player = playerRepo.findPlayerById(1L);
        }
        Long end = System.currentTimeMillis();
        log.info("Executed all read-write queries in "+(end-start)+"ms");

        start = System.currentTimeMillis();
        for (int i=0; i<100; i++) {
            ChessPlayer player = playerRepo.findPlayerReadOnlyById(1L);
        }
        end = System.currentTimeMillis();
        log.info("Executed all read-only queries in "+(end-start)+"ms");
    }
}
