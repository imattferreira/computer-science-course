package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.Type;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TypesRepository {
    static final String collection = "types";

    public TypesRepository() {
        new FirebaseService().run();
    }

    public Type repositoryMapper(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        DocumentReference ref = document.getReference();
        Type type = new Type();
        type.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    type.setName((String) entry.getValue());
                    break;
                case "id":
                    type.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped TypesRepository " + entry.getKey());
                    break;
            }
        }

        return type;
    }

    public Type repositoryMapper(DocumentReference ref, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        Type type = new Type();

        type.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    type.setName((String) entry.getValue());
                    break;
                case "id":
                    type.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped TypesRepository " + entry.getKey());
                    break;
            }
        }

        return type;
    }

    public ArrayList<Type> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<Type> result = new ArrayList<Type>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public Type findByRef(DocumentReference ref) {
        try {
            return this.repositoryMapper(ref, ref.get().get());
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
