package com.thorben.janssen.spring.data.model;

public interface BetterPlayerFullNameIntf {
    
    String getFirstName();
    String getLastName();

    default String getFullName() {return getLastName()+", "+getFirstName();}
}
