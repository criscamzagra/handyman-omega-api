package com.co.ias.Handyman.application.technical.model;

import com.co.ias.Handyman.application.person.domain.PersonId;
import com.co.ias.Handyman.application.technical.domain.Technical;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;


public class TechnicalDTO {

    /*
     Technical DTO Attributes
    */


    private Integer technicalId;


    private  Integer personId;


    /*
      No Args Constructor
    */
    public TechnicalDTO(){

    }

    /*
     All Args Constructor
    */

    public TechnicalDTO(Integer technicalId, Integer personId) {
        this.technicalId = technicalId;
        this.personId = personId;

    }



    /*
      Method to domain
    */
    public Technical toDomain(){
        return new Technical(
                new TechnicalId(technicalId),
                new PersonId(personId)

        );
    }

    /*
     Method from domain
    */
    public static TechnicalDTO fromDomain(Technical technical){
        return new TechnicalDTO(
                technical.getTechnicalID().getValue(),
                technical.getPersonId().getValue()
        );
    }

    /*
      Getters and Setters
    */

    public Integer getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(Integer technicalId) {
        this.technicalId = technicalId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }




}
