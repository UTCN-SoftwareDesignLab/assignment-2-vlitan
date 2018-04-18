package main.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import main.sample.ClientCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class GoogleRecomandationService implements RecomandationService<Volume> {
    /**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     */
    private static final String APPLICATION_NAME = "ru/1.0";
    @Override
    public List<Volume> recomendByTitle(String title) throws GeneralSecurityException, IOException {
        ClientCredentials.errorIfNotSpecified();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        // Set up Books client.
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(ClientCredentials.API_KEY))
                .build();
        return (List<Volume>) books.volumes().list("intitle:"+title);
    }
}
