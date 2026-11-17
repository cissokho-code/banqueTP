package dao;

import config.Connexion;
import model.Compte;
import model.Proprietaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompteDAO {
    // 1- ajoute
    public void ajouterCompte(Compte c){
        String sql = "INSERT INTO compte(numero,solde,id_proprietaire)  VALUES (?,?,?) ";
        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);){
            ps.setString(1,c.getNumero());
            ps.setDouble(2,c.getSolde());
            ps.setInt(3,c.getId_proprietaire());
            ps.executeUpdate();

        }catch (SQLException ex){
            System.out.println("Erreur : "+ex.getMessage());
        }
    }

    // 2- modification
    public void modifierCompte(Compte c){
        String sql = "UPDATE compte SET numero=?,solde=?, WHERE id_proprietaire=?";
        try(Connection cn = Connexion.ouvrir();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1,c.getNumero());
            ps.setDouble(2,c.getSolde());
            ps.setInt(3,c.getId_proprietaire());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Erreur !!!!!"+ ex.getMessage());
        }
    }
    //  3- suppression
    public Proprietaire supprimerUnCompte(Compte c){
        String sql = "DELETE FROM compte  WHERE id_proprietaire=?";
        try(Connection cn = Connexion.ouvrir();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1,c.getId_proprietaire());
            ps.executeUpdate();

        }catch(SQLException ex){
            System.out.println("Erreur !!!!!"+ ex.getMessage());
        }
        return null;
    }

    // 4- affichage
    public ArrayList<Compte> getCompte(){
        ArrayList<Compte> comptes = new ArrayList<>();
        String sql = "SELECT numero,solde,id_proprietaire FROM compte ";
        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                String numero = rs.getString("numero");
                double solde = rs.getDouble("solde");
                int id_proprietaire = rs.getInt("id_proprietaire");

                Compte c = new Compte(numero,solde,id_proprietaire);
                comptes.add(c);

            }

        }catch (SQLException ex){
            System.out.println("Erreur Lors de l'affichage "+ex.getMessage());
        }
        return comptes;

    }
    // 5- rechercher

    public Compte rechercherUnCompte(int id_proprietaire){

        String sql = "SELECT numero,solde,id_proprietaire FROM compte  where id_proprietaire = ? ";
        try (Connection cn = Connexion.ouvrir();
             PreparedStatement ps = cn.prepareStatement(sql);){
            ps.setInt(1,id_proprietaire);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String numero = rs.getString("numero");
                double solde = rs.getDouble("solde");
                int id_Proprietaire = rs.getInt("id_proprietaire");
                Compte c = new Compte(numero,solde,id_Proprietaire);
                return c;

            }

        }catch (SQLException ex){
            System.out.println("Erreur Lors de l'affichage "+ex.getMessage());
        }
        return null;

    }
}
