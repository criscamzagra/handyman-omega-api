package com.co.ias.Handyman.application.typeIdentification.ports.out;



import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentification;
import com.co.ias.Handyman.application.typeIdentification.domain.TypeIdentificationId;

public interface TypeIdentificationRepository {

    Integer store(TypeIdentification typeIdentification);
    Integer getId(TypeIdentificationId typeIdentificationId);
    TypeIdentification update(TypeIdentification typeIdentification);
    Integer delete(TypeIdentificationId typeIdentificationId);


}
