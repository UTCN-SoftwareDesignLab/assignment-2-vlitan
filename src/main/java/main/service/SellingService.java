package main.service;

import main.model.Book;
import main.util.Notification;

public interface SellingService {
    Notification<Boolean> sell(Book book, int quantity);
    Notification<Boolean> sellById(int id, int quantity);
}
