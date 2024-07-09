package com.co.ias.Handyman.application.typeIdentification.domain;

public class TypeIdentification {

    private final TypeIdentificationId typeIdentificationId;
    private final TypeIdentificationName typeIdentificationName;
    private final TypeIdentificationDescription typeIdentificationDescription;

    public TypeIdentification(TypeIdentificationId typeIdentificationId, TypeIdentificationName typeIdentificationName, TypeIdentificationDescription typeIdentificationDescription) {
        this.typeIdentificationId = typeIdentificationId;
        this.typeIdentificationName = typeIdentificationName;
        this.typeIdentificationDescription = typeIdentificationDescription;
    }



    public TypeIdentificationId getTypeIdentificationId() {
        return typeIdentificationId;
    }

    public TypeIdentificationName getTypeIdentificationName() {
        return typeIdentificationName;
    }

    public TypeIdentificationDescription getTypeIdentificationDescription() {
        return typeIdentificationDescription;
    }
}
