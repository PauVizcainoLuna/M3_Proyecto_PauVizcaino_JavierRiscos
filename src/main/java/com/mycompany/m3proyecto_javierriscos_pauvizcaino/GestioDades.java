package com.mycompany.m3proyecto_javierriscos_pauvizcaino;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;

public class GestioDades {
   public ObservableList<Usuari> llistaUsuaris() { 
        ObservableList<Usuari> llistaUsuaris = FXCollections.observableArrayList();
        String sql = "select nif,nom, dataNaixement,telefon, correu, imatge from usuaris";
        //String sql="select nom from usuaris";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordre = connection.createStatement();
            ResultSet resultSet = ordre.executeQuery(sql);
            while (resultSet.next()) {
                llistaUsuaris.add(
                        new Usuari(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDate(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                new Image(resultSet.getBlob(6).getBinaryStream())
                        )
                );
            }

            connection.close();

        } catch (SQLException throwables) {
            System.out.println("Error:"+throwables.getMessage());
        }
        return llistaUsuaris;
    }

    public boolean modificarUsuari(Usuari usuari) {
        boolean ok = false;
        String sql = "UPDATE usuaris SET nom=?,dataNaixement=?,telefon=?,correu=? WHERE nif=?";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, usuari.getNom());
            ordre.setDate(2, Date.valueOf(usuari.getDataNaixement()));
            ordre.setString(3, usuari.getTelefon());
            ordre.setString(4, usuari.getCorreu());
            ordre.setString(5, usuari.getNif());
            ordre.executeUpdate();
            ok = true;
            System.out.println("usuari modificat");
        } catch (SQLException e) {
            System.out.println("Error SQL:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return ok;
    }

    public boolean eliminarUsuari(Usuari usuari) {
        boolean ok = false;
        String sql = "DELETE FROM usuaris WHERE nif=?";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, usuari.getNif());
            ok = ordre.executeUpdate() > 0;
        } catch (SQLException throwables) {
            System.out.println("Error:"+throwables.getMessage());
        }
        return ok;
    }

    public boolean afegeixUsuari(Usuari usuari) throws SQLException, FileNotFoundException, IOException {
          boolean ok = false;   
        Connection connection = new Connexio().connecta();
        String sql = "INSERT INTO usuaris VALUES (?,?,?,?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {
            ordre.setString(1, usuari.getNif());
            ordre.setString(2, usuari.getNom());
            ordre.setDate(3, Date.valueOf(usuari.getDataNaixement()));
            ordre.setString(4, usuari.getTelefon());
            ordre.setString(5, usuari.getCorreu());
            Image ima = usuari.getImatgeJ();       
            BufferedImage imagenB=SwingFXUtils.fromFXImage(ima, null);                   
                
            ByteArrayOutputStream s=new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(imagenB, "jpg", s);
            
            byte[] imaBytes=s.toByteArray();
            Blob b=connection.createBlob();
            b.setBytes(1,imaBytes);            
            ordre.setBlob(6, b);
            ordre.executeUpdate();
            ok = true;

        } catch (SQLException throwables) {
            System.out.println("Error:"+throwables.getMessage());
        }

        return ok;
    }
}
