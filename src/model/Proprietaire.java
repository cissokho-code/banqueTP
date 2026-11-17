package model;

public class Proprietaire {
    private int id;
    private String nom;
    private String prenom;
    private String adrese;

    public Proprietaire(String nom, String prenom, String adrese){
        this.nom = nom;
        this.prenom = prenom;
        this.adrese = adrese;
    }
    public Proprietaire(int id , String nom, String prenom, String adrese){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adrese = adrese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdrese(String adrese) {
        this.adrese = adrese;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdrese() {
        return adrese;
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " " + adrese;
    }
}
