package com.co.ias.Handyman.application.technical.domain;


import com.co.ias.Handyman.application.person.domain.*;


public class Technical {


    /*
          id_technical
          id_person
        */
    private final TechnicalId technicalID;
    private final PersonId personId;



    public Technical(TechnicalId technicalID, PersonId personId) {
        this.technicalID = technicalID;
        this.personId = personId;

    }

    public TechnicalId getTechnicalID() {
        return technicalID;
    }

    public PersonId getPersonId() {
        return personId;
    }


}
