package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class Type {
    private String id;
    private String name;
    private DocumentReference ref;

    public Type(String name) {
        this.name = name;
    }

    public Type() {}

    public String getName() {
        return name;
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

    public DocumentReference getRef() {
        return ref;
    }

    public void setRef(DocumentReference ref) {
        this.ref = ref;
    }
}
