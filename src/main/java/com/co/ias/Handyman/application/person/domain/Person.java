package com.co.ias.Handyman.application.person.domain;

import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationId;

public class Person {

    private final PersonId personId;
    private final PersonName personName;
    private final PersonIdentification personIdentification;
    private final TypeIdentificationId idTypeIdentification;



    public Person(PersonId personId, PersonName personNames, PersonIdentification personIdentification, TypeIdentificationId idTypeIdentification) {
        this.personId = personId;
        this.personName = personNames;
        this.personIdentification = personIdentification;
        this.idTypeIdentification = idTypeIdentification;
    }



    public PersonId getPersonId() {
        return personId;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public PersonIdentification getPersonIdentification() {
        return personIdentification;
    }

    public TypeIdentificationId getIdTypeIdentification() {
        return idTypeIdentification;
    }
}
