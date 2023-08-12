package org.aps.implementations;

import com.google.cloud.firestore.DocumentReference;

public class ProtectionLevel {
    private String id;
    private int level;
    private DocumentReference ref;

    public ProtectionLevel(int level) {
        this.level = level;
    }

    public ProtectionLevel() {}

    public ProtectionLevel(DocumentReference ref) {
        this.ref = ref;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
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
