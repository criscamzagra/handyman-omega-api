package com.co.ias.Handyman.application.person.model;

import com.co.ias.Handyman.application.person.domain.*;
import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationId;

public class PersonDTO {

    /*
      Person DTO Attributes
    */
    private Integer personId;
    private String personNames;
    private String personIdentification;
    private Integer idTypeIdentification;

    /*
      No Args Constructor
    */
    public PersonDTO() {
    }

    /*
     All Args Constructor
    */
    public PersonDTO(Integer personId, String personNames, String personIdentification, Integer idTypeIdentification) {
        this.personId = personId;
        this.personNames = personNames;
        this.personIdentification = personIdentification;
        this.idTypeIdentification = idTypeIdentification;
    }

    /*
      Method to domain
    */
    public Person toDomain(){
        return new Person(
                new PersonId(personId),
                new PersonName(personNames),
                new PersonIdentification(personIdentification),
                new TypeIdentificationId(idTypeIdentification)
        );
    }


    /*
      Method from domain
    */
    public static PersonDTO fromDomain(Person person){
        return new PersonDTO(
                person.getPersonId().getValue(),
                person.getPersonName().getValue(),
                person.getPersonIdentification().getValue(),
                person.getIdTypeIdentification().getValue()
        );

    }

    /*
      Getters and Setters
    */
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonNames() {
        return personNames;
    }

    public void setPersonNames(String personNames) {
        this.personNames = personNames;
    }

    public String getPersonIdentification() {
        return personIdentification;
    }

    public void setPersonIdentification(String personIdentification) {
        this.personIdentification = personIdentification;
    }

    public Integer getIdTypeIdentification() {
        return idTypeIdentification;
    }

    public void setIdTypeIdentification(Integer idTypeIdentification) {
        this.idTypeIdentification = idTypeIdentification;
    }
}
