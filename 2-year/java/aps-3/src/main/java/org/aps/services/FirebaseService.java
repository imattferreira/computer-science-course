package org.aps.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.*;

public class FirebaseService {
    public static Firestore repository;

    /**
     * This is a Singleton pattern, utilized to force all application uses a unique connection of Firestore,
     * preventing memory issues and max-simultaneously connections error.
     */
    public void run() {
        if (repository == null) {
            this.instance();
        }
    }

    private void instance() {
        try {
            String filepath = new File("./src/main/java/org/aps/configs/serviceAccount.json").getAbsolutePath();
            InputStream serviceAccount = new FileInputStream(filepath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            FirebaseService.repository = FirestoreClient.getFirestore();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        } catch (IOException exception) {
            System.out.println("Credentials error");
        }
    }
}
