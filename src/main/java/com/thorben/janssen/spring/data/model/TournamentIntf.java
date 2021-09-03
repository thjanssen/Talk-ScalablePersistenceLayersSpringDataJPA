package com.thorben.janssen.spring.data.model;

import java.util.List;

public interface TournamentIntf {
    
    String getName();
    List<PlayerNameIntf> getPlayers();
}
