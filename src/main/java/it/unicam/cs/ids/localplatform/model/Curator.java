package it.unicam.cs.ids.localplatform.model;

public class Curator extends AuthorizedContributor {
    public Curator(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
    }
}