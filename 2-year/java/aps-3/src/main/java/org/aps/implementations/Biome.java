package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class Biome {
    private String id;
    private String name;
    private DocumentReference ref = null;

    public Biome(String name) {
        this.name = name;
    }

    public Biome() {}


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

    public DocumentReference getRef() {
        return ref;
    }

    public void setRef(DocumentReference ref) {
        this.ref = ref;
    }
}
