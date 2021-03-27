package com.example.test.model;

public class Document {
    private int documentOfUnitId;
    private String documentOfUnitName;
    private int documentTypeId;
    private int unitId;

    public int getDocumentOfUnitId() {
        return documentOfUnitId;
    }

    public void setDocumentOfUnitId(int documentOfUnitId) {
        this.documentOfUnitId = documentOfUnitId;
    }

    public String getDocumentOfUnitName() {
        return documentOfUnitName;
    }

    public void setDocumentOfUnitName(String documentOfUnitName) {
        this.documentOfUnitName = documentOfUnitName;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
