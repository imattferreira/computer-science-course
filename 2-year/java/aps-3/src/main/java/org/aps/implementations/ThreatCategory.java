package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class ThreatCategory {
    private String id;
    private String name;
    private String acronym;
    private DocumentReference ref;

    public ThreatCategory(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public ThreatCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentReference getRef() {
        return ref;
    }

    public void setRef(DocumentReference ref) {
        this.ref = ref;
    }
}
