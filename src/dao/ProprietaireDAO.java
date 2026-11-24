package dao;

import config.Connexion;
import model.Proprietaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProprietaireDAO {
    // 1- sauvogarder
   public void ajouterProprietaire(Proprietaire p){
       String sql = "INSERT INTO Proprietaire(nom,prenom,adresse) VALUES(?,?,?)";
       try(Connection cn = Connexion.ouvrir();
           PreparedStatement ps = cn.prepareStatement(sql)){
           ps.setString(1,p.getNom());
           ps.setString(2,p.getPrenom());
           ps.setString(3,p.getAdrese());
           ps.executeUpdate();

       }catch(SQLException ex){
           System.out.println("Erreur !!!!!"+ ex.getMessage());
       }
   }
   // 2- affichage
   public ArrayList<Proprietaire> getProprietaires(){
       ArrayList<Proprietaire> proprietaires = new ArrayList<>();
       String sql = "SELECT id,nom,prenom,adresse FROM Proprietaire ";
       try (Connection cn = Connexion.ouvrir();
       PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
           while (rs.next()){
               int id = rs.getInt("id");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               String adresse = rs.getString("adresse");
               Proprietaire p = new Proprietaire(id,nom,prenom,adresse);
               proprietaires.add(p);

           }

       }catch (SQLException ex){
           System.out.println("Erreur Lors de l'affichage "+ex.getMessage());
       }
    return proprietaires;

   }
   // 3- recherche par id

    public Proprietaire rechercherProprietaires(int id){

        String sql = "SELECT id,nom,prenom,adresse FROM Proprietaire  where id = ? ";
        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);){
             ps.setInt(1,id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                Proprietaire p = new Proprietaire(id,nom,prenom,adresse);
            return p;

            }

        }catch (SQLException ex){
            System.out.println("Erreur Lors de l'affichage "+ex.getMessage());
        }
        return null;

    }
    //  4- modifier

    public void modifierProprietaire(Proprietaire p){
        String sql = "UPDATE Proprietaire SET nom=?,prenom=?,adresse=? WHERE id=?";
        try(Connection cn = Connexion.ouvrir();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getAdrese());
            ps.setInt(4,p.getId());
            ps.executeUpdate();

        }catch(SQLException ex){
            System.out.println("Erreur !!!!!"+ ex.getMessage());
        }
    }
    // 5- suppression
    public Proprietaire supprimerProprietaire(Proprietaire p){
        String sql = "DELETE FROM Proprietaire  WHERE id=?";
        try(Connection cn = Connexion.ouvrir();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1,p.getId());
            ps.executeUpdate();

        }catch(SQLException ex){
            System.out.println("Erreur !!!!!"+ ex.getMessage());
        }
        return null;
    }
}
