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
            switch (choix = Integer.parseInt(sc.nextLine())){
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
                    System.out.println("Merci de nous Avoire choisi");
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
        System.out.println("9. Rechercher un Compte par id_proprietaire");
        System.out.println("9. Modifier un Compte");
        System.out.println("10. Supprimer un Compte");

        System.out.print("choisi votre operation : ");
    }
    // -------------------------------- Proprietaire -----------------------------
    // 1- ajout Proprietaire
    public void ajoutProprietaire(){
        String nom,prenom,adresse;
        int choix =0 ;
        int continuer =0 ;
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
           continuer = Integer.parseInt(sc.nextLine());

       }while (continuer ==1);
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
        int id ;
        System.out.println("");
        System.out.print("Donner L'id a rechercher : ");
        id = Integer.parseInt(sc.nextLine());
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
            System.out.println(p);
        }
        else {
            System.out.println( "l'id "+id+" n'existe pas !!!");
        }
        System.out.println("");
    }

    // 4- modification
    public void modifierProprietaire(){
        int id , nouvId;
        String nouvNom,nouvPrenom,nouvAdresse;
        System.out.print("Donner L'id a Modifier : ");
        id = Integer.parseInt(sc.nextLine());
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
            System.out.print("donnez le nouveau Id : ");
            nouvId = Integer.parseInt(sc.nextLine());
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
    }

    //5- sppression
    public void supprimerProprietaire() {
      int id ;
        System.out.print("Donner L'id a Supprimer : ");
        id = Integer.parseInt(sc.nextLine());
        Proprietaire p = pDAO.rechercherProprietaires(id);
        if (p != null){
           pDAO.supprimerProprietaire(p);
            System.out.println("Proprietaire supprimer avec succès");
        }
        else {
            System.out.println( "Aucun proprietaire trouve  !!! ");
        }

    }

//------------------------------- compte------------------------------------------
    // 6- ajoute d'un compte
    public void ajoutDunCompte(){
        String numero;
        double solde;
        int id_proprietaire ,continuer;
       do {
           System.out.print("Donnez le numero du Compte : ");
           numero = sc.nextLine();
           System.out.print("Donnez la solde a ajouteter : ");
           solde = Double.parseDouble(sc.nextLine());
           System.out.print("Donnez l'id du proprietaire : ");
           id_proprietaire = Integer.parseInt(sc.nextLine());
           Compte c = new Compte(numero,solde,id_proprietaire);
           cDAO.ajouterCompte(c);
           System.out.println("Tapez 1 pour contunier 0 pour Quiter");
           continuer = Integer.parseInt(sc.nextLine());
       }while (continuer == 1);
    }
    // 7- affichage
    public void afficherCompte(){
        ArrayList<Compte> comptes = cDAO.getCompte();
        System.out.println("============================== Affichage des comptes  ============================== ");
        for (Compte c : comptes){
            System.out.println(c);
        }
        System.out.println("============================== Fin d'affichage  ============================== ");
    }
    // 8- recherche
    public void rechercheCompte (){
        int id_proprietaire ;
        System.out.println("");
        System.out.print("Donner L'id a rechercher : ");
        id_proprietaire = Integer.parseInt(sc.nextLine());
       Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            System.out.println(c);
        }
        else {
            System.out.println( "Aucune compte trouve !!!");
        }
        System.out.println("");
    }
    // 9- modifier compte
    public void modifierCompte(){
        int nouvId_proprietaire,id_proprietaire;
         String nouvNumero;
         double nouvSolde;
        System.out.print("Donner L'id a Modifier : ");
        id_proprietaire = Integer.parseInt(sc.nextLine());
        Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            System.out.print("donnez le nouveau Id : ");
            nouvNumero = sc.nextLine();
            System.out.print("donnez le nouveau Numero : ");
            nouvSolde =Double.parseDouble(sc.nextLine());
            System.out.print("donnez le nouveau Solde : ");
            nouvId_proprietaire =Integer.parseInt(sc.nextLine());
            c.setNumero(nouvNumero);
            c.setSolde(nouvSolde);
            c.setId_proprietaire(nouvId_proprietaire);
           cDAO.modifierCompte(c);
            System.out.println("Compte modifier avec succès");
        }
        else {
            System.out.println( "Aucun Compte trouve  !!! ");
        }
    }

    // 10- suppression
    public void supprimerUnCompte() {
        int id_proprietaire ;
        System.out.print("Donner L'id a Supprimer : ");
        id_proprietaire = Integer.parseInt(sc.nextLine());
        Compte c = cDAO.rechercherUnCompte(id_proprietaire);
        if (c != null){
            cDAO.supprimerUnCompte(c);
            System.out.println("Compte supprimer avec succès");
        }
        else {
            System.out.println( "Aucun Compte trouve  !!! ");
        }

    }
}
