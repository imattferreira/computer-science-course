package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class Group {
    private String id;
    private String name;
    private DocumentReference ref;

    public Group(String name) {
        this.name = name;
    }

    public Group() {}

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentReference getRef() {
        return ref;
    }

    public void setRef(DocumentReference ref) {
        this.ref = ref;
    }
}
