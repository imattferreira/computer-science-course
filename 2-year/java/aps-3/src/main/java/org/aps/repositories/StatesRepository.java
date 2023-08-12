package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.State;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class StatesRepository {
    static final String collection = "states";

    public StatesRepository() {
        new FirebaseService().run();
    }

    public State repositoryMapper(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        DocumentReference ref = document.getReference();
        State state = new State();
        state.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    state.setName((String) entry.getValue());
                    break;
                case "uf":
                    state.setUf((String) entry.getValue());
                    break;
                case "id":
                    state.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped StatesRepository " + entry.getKey());
                    break;
            }
        }

        return state;
    }

    public State repositoryMapper(DocumentReference ref, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        State state = new State();

        state.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    state.setName((String) entry.getValue());
                    break;
                case "uf":
                    state.setUf((String) entry.getValue());
                    break;
                case "id":
                    state.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped StatesRepository " + entry.getKey());
                    break;
            }
        }

        return state;
    }

    public ArrayList<State> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<State> result = new ArrayList<State>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public State findByRef(DocumentReference ref) {
        try {
            return this.repositoryMapper(ref, ref.get().get());
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
