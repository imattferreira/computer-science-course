package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class State {
    private String id;
    private String name;
    private String uf;
    private DocumentReference ref;

    public State(String uf) {
        this.uf = uf;
    }

    public State() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public DocumentReference getRef() {
        return ref;
    }

    public void setRef(DocumentReference ref) {
        this.ref = ref;
    }
}
