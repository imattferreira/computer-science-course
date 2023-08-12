package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.Group;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class GroupiesRepository {
    static final String collection = "groupies";
    public GroupiesRepository() {
        new FirebaseService().run();
    }

    public Group repositoryMapper(QueryDocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        DocumentReference ref = document.getReference();
        Group group = new Group();
        group.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    group.setName((String) entry.getValue());
                    break;
                case "id":
                    group.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped GroupiesRepository " + entry.getKey());
                    break;
            }
        }

        return group;
    }

    public Group repositoryMapper(DocumentReference ref, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        Group group = new Group();

        group.setRef(ref);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    group.setName((String) entry.getValue());
                    break;
                case "id":
                    group.setId((String) entry.getValue());
                    break;
                default:
                    System.out.println("key not mapped GroupiesRepository " + entry.getKey());
                    break;
            }
        }

        return group;
    }

    public ArrayList<Group> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<Group> result = new ArrayList<Group>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public Group findByRef(DocumentReference ref) {
        try {
            return this.repositoryMapper(ref, ref.get().get());
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
