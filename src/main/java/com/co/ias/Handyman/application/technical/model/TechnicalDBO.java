package com.co.ias.Handyman.application.technical.model;

import com.co.ias.Handyman.application.person.domain.PersonId;
import com.co.ias.Handyman.application.technical.domain.Technical;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "technical")
public class TechnicalDBO {

    @Id
    @Column(name = "id_technical")
    private Integer technicalId;
    @Column(name = "id_person")
    private Integer personId;

    public TechnicalDBO() {
    }

    public TechnicalDBO(Integer technicalId, Integer personId) {
        this.technicalId = technicalId;
        this.personId = personId;
    }


    public static TechnicalDBO fromResultSet(ResultSet resultSet) throws SQLException  {
        TechnicalDBO technicalDBO = new TechnicalDBO();
        technicalDBO.setTechnicalId(resultSet.getInt("id_technical"));
        technicalDBO.setPersonId(resultSet.getInt("id_person"));

        return technicalDBO;

    }


    public Technical toDomain(){
        return new Technical(
                new TechnicalId(technicalId),
                new PersonId(personId)
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
