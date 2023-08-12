package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.ProtectionLevel;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ProtectionLevelsRepository {
    static final String collection = "protection_levels";

    public ProtectionLevelsRepository() {
        new FirebaseService().run();
    }

    public ProtectionLevel repositoryMapper(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        DocumentReference ref = document.getReference();
        ProtectionLevel protectionLevel = new ProtectionLevel();
        protectionLevel.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "level":
                    protectionLevel.setLevel((int) entry.getValue());
                    break;
                case "id":
                    protectionLevel.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped ProtectionLevels " + entry.getKey());
                    break;
            }
        }

        return protectionLevel;
    }

    public ProtectionLevel repositoryMapper(DocumentReference ref, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        ProtectionLevel protectionLevel = new ProtectionLevel();

        protectionLevel.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "level":
                    protectionLevel.setLevel((int) entry.getValue());
                    break;
                case "id":
                    protectionLevel.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped ProtectionLevels " + entry.getKey());
                    break;
            }
        }

        return protectionLevel;
    }

    public ArrayList<ProtectionLevel> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<ProtectionLevel> result = new ArrayList<ProtectionLevel>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ProtectionLevel findByRef(DocumentReference ref) {
        try {
            return this.repositoryMapper(ref, ref.get().get());
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
