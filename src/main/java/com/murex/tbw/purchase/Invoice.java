package com.murex.tbw.purchase;

import com.murex.tbw.IdGenerator;
import com.murex.tbw.domain.country.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Invoice {
    private final int id;
    private final String clientName;
    private final List<PurchasedBook> purchasedBooks;
    private final Country country;

    public Invoice(String clientName, Country country) {
        this(IdGenerator.nextId(), clientName, country);
    }

    public Invoice(int id, String clientName, Country country) {
        this.id = id;
        this.clientName = clientName;
        this.country = country;
        this.purchasedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public Country getCountry() {
        return country;
    }

    public void addPurchasedBooks(List<PurchasedBook> books) {
        purchasedBooks.addAll(books);
    }

    public void addPurchasedBook(PurchasedBook book) {
        this.purchasedBooks.add(book);
    }

    public double computeTotalAmount() {
        return purchasedBooks.stream().mapToDouble(PurchasedBook::getTotalPrice).sum();
    }

    public List<PurchasedBook> getPurchasedBooks() {
        return purchasedBooks;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", purchasedBooks=" + purchasedBooks +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                Objects.equals(clientName, invoice.clientName) &&
                Objects.equals(purchasedBooks, invoice.purchasedBooks) &&
                Objects.equals(country, invoice.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientName, purchasedBooks, country);
    }
}
