import dao.CompteDAO;
import dao.ProprietaireDAO;
import model.Compte;
import model.Proprietaire;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
   Scanner sc = new Scanner(System.in);
    ProprietaireDAO pDAO= new ProprietaireDAO();
    CompteDAO cDAO = new CompteDAO();
    void main() {
        int choix ;
        do {
            affichageMenu();
            switch (choix = lireUNnombreEntier()){
                case 1:
                    ajoutProprietaire();
                    break;
                case 2:
                    afficherProprietaire();
                    break;
                case 3:
                    rechercheProprietaire();
                    break;
                case 4:
                    modifierProprietaire();
                    break;
                case 5:
                    supprimerProprietaire();
                    break;
                case 6:
                    ajoutDunCompte();
                    break;
                case 7:
                    afficherCompte();
                    break;
                case 8:
                    rechercheCompte();
                    break;
                case 9:
                    modifierCompte();
                    break;
                case 10:
                    supprimerUnCompte();
                    break;
                case 0:
                    System.out.println("bye bye !!!!");
                    break;
                default:
                    System.out.println("Merci D'Avoire choisi notre Banque");
            }
        }while (choix != 0);
    }
    // 0- Menu d'affichage
    public void affichageMenu(){
        System.out.println("******************************* Menu Banque *******************************");
        System.out.println("1. Ajouter un proprietaire");
        System.out.println("2. Afficher les proprietaires");
        System.out.println("3. Rechercher un proprietaire par ID");
        System.out.println("4. Modifier un proprietaire");
        System.out.println("5. Supprimer un proprietaire");
        System.out.println("================ compte =====================");
        System.out.println("6. Ajouter un Compte");
        System.out.println("7. Afficher les Comptes");
        System.out.println("8. Rechercher un Compte par id_proprietaire");
        System.out.println("9. Modifier un Compte");
        System.out.println("10.Supprimer un Compte");

        System.out.print("choisissz votre operation : ");
    }
    // -------------------------------- Proprietaire -----------------------------
    // 1- ajout Proprietaire
    public void ajoutProprietaire(){
        String nom,prenom,adresse;
        int continuer ;
        System.out.println("------------------ Ajout d'un Proprietaire ------------------");
       do {
           System.out.print("Nom Proprietaire : ");
           nom = sc.nextLine();
           System.out.print("Prenom Proprietaire : ");
           prenom = sc.nextLine();
           System.out.print("Adresse Proprietaire : ");
           adresse = sc.nextLine();
           Proprietaire p = new Proprietaire(nom,prenom,adresse);
           pDAO.ajouterProprietaire(p);

           System.out.println("Tapez 1 pour contunier 0 pour Quiter");
           continuer = lireUNnombreEntier();

       }while (continuer ==1);
        System.out.println("=============================  Fin D'Ajout ============================");
    }
    // 2-   Affichage Proprietaire
    public void afficherProprietaire(){
        ArrayList<Proprietaire> proprietaires = pDAO.getProprietaires();
        System.out.println("============================== Affichage des proprietaires ============================== ");
        for (Proprietaire p : proprietaires){
            System.out.println(p);
        }
        System.out.println("============================== Fin d'affichage  ============================== ");
    }
    // 3- recherche
    public void rechercheProprietaire (){
        System.out.println("============================== Rechercheer un proprietaire ============================== ");

        int id ;
        System.out.println("");
        System.out.print("Donner L'id a rechercher : ");
        id = lireUNnombreEntier();
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
            System.out.println(p);
        }
        else {
            System.out.println( "l'id "+id+" n'existe pas !!!");
        }
        System.out.println("");
        System.out.println("============================== Fin de recherche  ============================== ");
    }

    // 4- modification
    public void modifierProprietaire(){
        System.out.println("============================== Modification D'un proprietaire ============================== ");
        int id , nouvId;
        String nouvNom,nouvPrenom,nouvAdresse;
        System.out.print("Donner L'id a Modifier : ");
        id = lireUNnombreEntier();
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
            System.out.print("donnez le nouveau Id : ");
            nouvId = lireUNnombreEntier();
            System.out.print("donnez le nouveau Nom : ");
            nouvNom =sc.nextLine();
            System.out.print("donnez le nouveau Prenom : ");
            nouvPrenom =sc.nextLine();
            System.out.print("donnez le nouveau Adresse : ");
            nouvAdresse =sc.nextLine();
            p.setId(nouvId);
            p.setNom(nouvNom);
            p.setPrenom(nouvPrenom);
            p.setAdrese(nouvAdresse);
            pDAO.modifierProprietaire(p);
            System.out.println("Proprietaire modifier avec succès");
        }
        else {
            System.out.println( "Aucun proprietaire trouve  !!! ");
        }
        System.out.println("============================== Fin du Modification  ============================== ");
    }

    //5- sppression
    public void supprimerProprietaire() {
        System.out.println("============================== Suppression d'un proprietaire ============================== ");
      int id ;
        System.out.print("Donner L'id a Supprimer : ");
        id = lireUNnombreEntier();
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
           pDAO.supprimerProprietaire(p);
            System.out.println("Proprietaire supprimer avec succès");
        }
        else {
            System.out.println( "Aucun proprietaire trouve  !!! ");
        }
        System.out.println("============================== Fin du suppression ============================== ");
    }

//------------------------------- compte------------------------------------------
    // 6- ajoute d'un compte
    public void ajoutDunCompte(){
        System.out.println("******************************** Ajout d'un compte ********************************");
        String numero;
        double solde;
        int id_proprietaire ,continuer;
       do {
           System.out.print("Donnez le numero du Compte : ");
           numero = sc.nextLine();
           System.out.print("Donnez la solde a ajouter : ");
           solde = lireunNombreReel();
           System.out.print("Donnez l'id du proprietaire : ");
           id_proprietaire = lireUNnombreEntier();
           Compte c = new Compte(numero,solde,id_proprietaire);
           cDAO.ajouterCompte(c);
           System.out.println("Tapez 1 pour contunier 0 pour Quiter");
           continuer = lireUNnombreEntier();
       }while (continuer == 1);
        System.out.println("******************************** Fin D'Ajout ********************************");
    }
    // 7- affichage
    public void afficherCompte(){
        ArrayList<Compte> comptes = cDAO.getCompte();
        System.out.println("******************************** Affichage des comptes ******************************** ");
        for (Compte c : comptes){
            System.out.println(c);
        }
        System.out.println("********************************Fin d'affichage  ******************************** ");
    }
    // 8- recherche
    public void rechercheCompte (){
        System.out.println("******************************** Recherche d'un compte ********************************");
        int id_proprietaire ;
        System.out.println("");
        System.out.print("Donner L'id a rechercher : ");
        id_proprietaire = lireUNnombreEntier();
       Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            System.out.println(c);
        }
        else {
            System.out.println( "Aucune compte trouve !!!");
        }
        System.out.println("******************************** Fin du Recherche ********************************");
    }
    // 9- modifier compte
    public void modifierCompte(){
        System.out.println("******************************** Modification d'un compte ********************************");
        int id_proprietaire;

         double nouvSolde;
        System.out.print("Donner L'id Proprietaire : ");
        id_proprietaire = lireUNnombreEntier();
        Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            System.out.print("donnez le nouveau solde : ");
            nouvSolde = lireunNombreReel();
            c.setSolde(nouvSolde);
           cDAO.modifierCompte(c);
            System.out.println("solde modifier avec succès");
        }
        else {
            System.out.println( "Aucun Compte trouve  !!! ");
        }
        System.out.println("******************************** Fin du  Modification ********************************");
    }

    // 10- suppression
    public void supprimerUnCompte() {
        System.out.println("******************************** Suppression d'un compte ********************************");
        int id_proprietaire ;
        System.out.print("Donner L'id Proprietaire : ");
        id_proprietaire = lireUNnombreEntier();
        Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            cDAO.supprimerUnCompte(c);
            System.out.println("Compte supprimer avec succès");
        }
        else {
            System.out.println( "Aucun Compte trouve  !!! ");
        }
        System.out.println("******************************** Fin du  Suppression ********************************");
    }


    // methode pour lire  un nombre entier
    public int lireUNnombreEntier() {
        while (true) {
            try {
                int nb = Integer.parseInt(sc.nextLine());
                return nb;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Entrez un nombre entier");
            }
        }
    }

    // methode pour lire un nombre Reel / Double
    public double lireunNombreReel() {
        while (true) {
            try {
                double nb = Double.parseDouble(sc.nextLine());
                return nb;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Entrez un nombre");
            }
        }
    }
}
