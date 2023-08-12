package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.ThreatCategory;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ThreatCategoriesRepository {
    static final String collection = "threat_categories";

    public ThreatCategoriesRepository() {
        new FirebaseService().run();
    }

    public ThreatCategory repositoryMapper(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        DocumentReference ref = document.getReference();
        ThreatCategory threatCategory = new ThreatCategory();
        threatCategory.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    threatCategory.setName((String) entry.getValue());
                    break;
                case "acronym":
                    threatCategory.setAcronym((String) entry.getValue());
                    break;
                case "id":
                    threatCategory.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped ThreatCategoriesRepository " + entry.getKey());
                    break;
            }
        }

        return threatCategory;
    }

    public ThreatCategory repositoryMapper(DocumentReference ref, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        ThreatCategory threatCategory = new ThreatCategory();

        threatCategory.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    threatCategory.setName((String) entry.getValue());
                    break;
                case "acronym":
                    threatCategory.setAcronym((String) entry.getValue());
                    break;
                case "id":
                    threatCategory.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped ThreatCategoriesRepository " + entry.getKey());
                    break;
            }
        }

        return threatCategory;
    }

    public ArrayList<ThreatCategory> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<ThreatCategory> result = new ArrayList<ThreatCategory>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ThreatCategory findByRef(DocumentReference ref) {
        try {
            return this.repositoryMapper(ref, ref.get().get());
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
