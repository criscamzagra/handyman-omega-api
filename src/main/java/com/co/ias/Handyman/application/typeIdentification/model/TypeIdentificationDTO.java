package com.co.ias.Handyman.application.typeIdentification.model;

import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentification;
import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationDescription;
import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationId;
import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationName;

public class TypeIdentificationDTO {

    private Integer typeIdentificationId;
    private String typeIdentification;
    private String description;

    /*
      No Args Constructor
    */
    public TypeIdentificationDTO() {
    }

    /*
      All Args Constructor
    */
    public TypeIdentificationDTO(Integer typeIdentificationId, String typeIdentification, String description) {
        this.typeIdentificationId = typeIdentificationId;
        this.typeIdentification = typeIdentification;
        this.description = description;
    }

    /*
      Method To domain
    */
    public TypeIdentification toDomain(){
        return new TypeIdentification(
                new TypeIdentificationId(typeIdentificationId),
                new TypeIdentificationName(typeIdentification),
                new TypeIdentificationDescription(description)
        );
    }

    /*
     Method From Domain
    */
    public static TypeIdentificationDTO fromDomain(TypeIdentification typeIdentification){
        return new TypeIdentificationDTO(
                typeIdentification.getTypeIdentificationId().getValue(),
                typeIdentification.getTypeIdentificationName().getValue(),
                typeIdentification.getTypeIdentificationDescription().getValue()
        );
    }

}
