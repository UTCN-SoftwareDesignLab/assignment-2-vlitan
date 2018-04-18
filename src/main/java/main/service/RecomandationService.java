package main.service;

import com.google.api.services.books.model.Volume;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface RecomandationService<T> {
    public List<T> recomendByTitle(String title) throws GeneralSecurityException, IOException;
}
