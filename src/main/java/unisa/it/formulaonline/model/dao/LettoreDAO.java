package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LettoreDAO {


    public Lettore doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l  " +
                            " WHERE l.idLettore=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rs.getInt(1);
                Lettore lettore1 = new Lettore();
                lettore1.setIdLettore(rs.getInt(1));
                lettore1.setEmail(rs.getString(2));
                lettore1.setPassword(rs.getString(3));
                lettore1.setNickname(rs.getString(4));
                lettore1.setModeratore(rs.getBoolean(5));
                lettore1.setDataFineSospensione(rs.getDate(6));


                return lettore1;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lettore> doRetrieveAll() {
        List<Lettore> lettoreList = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lettore lettore = new Lettore();
                lettore.setIdLettore(rs.getInt(1));
                lettore.setEmail(rs.getString(2));
                lettore.setPassword(rs.getString(3));
                lettore.setNickname(rs.getString(4));
                lettore.setModeratore(rs.getBoolean(5));
                lettore.setDataFineSospensione(rs.getDate(6));

                lettoreList.add(lettore);
            }
            return lettoreList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void doSave(Lettore lettore) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.lettore (email, pass, nickname, scuderiaPreferita, moderatore, dataFineSospensione)" +
                            "  VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, lettore.getEmail());
            ps.setString(2, lettore.getPassword());
            ps.setString(3, lettore.getNickname());
            ps.setString(4, lettore.getScuderiaPref());
            ps.setBoolean(5, lettore.getModeratore());
            ps.setDate(6, (Date) lettore.getDataFineSospensione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
