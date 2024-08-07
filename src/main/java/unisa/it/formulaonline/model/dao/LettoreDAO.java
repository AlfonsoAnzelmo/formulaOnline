package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione dei lettori nel database.
 */
public class LettoreDAO {
    /**
     * Metodo che restituisce un lettore partendo dal suo identificativo
     * @param id relativo al lettore da recuperare
     * @return il lettore se esiste, null altrimenti
     */
    public Lettore doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l  " +
                            " WHERE l.idLettore=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Lettore lettore = new Lettore();
                lettore.setIdLettore(rs.getInt(1));
                lettore.setEmail(rs.getString(2));
                lettore.setPassword(rs.getString(3));
                lettore.setNickname(rs.getString(4));
                lettore.setScuderiaPref(rs.getString(5));
                lettore.setModeratore(rs.getBoolean(6));
                if(rs.getDate(7) == null)
                    lettore.setDataFineSospensione(null);
                else
                    lettore.setDataFineSospensione(new java.util.Date(rs.getDate(7).getTime()));


                return lettore;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per recuperare un lettore partendo da email e password, usato per il login
     * @param email relativo al lettore
     * @param password dell'account
     * @return il lettore se le credenziali sono corrette, null altrimenti
     */
    public Lettore doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l  " +
                            " WHERE l.email=? AND BINARY l.pass=?");

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.getInt(1);
                Lettore lettore = new Lettore();
                lettore.setIdLettore(rs.getInt(1));
                lettore.setEmail(rs.getString(2));
                lettore.setPassword(rs.getString(3));
                lettore.setNickname(rs.getString(4));
                lettore.setScuderiaPref(rs.getString(5));
                lettore.setModeratore(rs.getBoolean(6));
                Date dataFine = rs.getDate(7);
                if(dataFine!=null){
                    lettore.setDataFineSospensione(new java.util.Date(dataFine.getTime()));
                }

                return lettore;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resituisce tutti i lettori
     * @return la lista di tutti i lettori, la lista sarà vuota se non esistono lettori
     */
    public List<Lettore> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l");

            ResultSet rs = ps.executeQuery();
            List<Lettore> lettoreList = new ArrayList<>();
            while (rs.next()){
                Lettore lettore = new Lettore();
                lettore.setIdLettore(rs.getInt(1));
                lettore.setEmail(rs.getString(2));
                lettore.setPassword(rs.getString(3));
                lettore.setNickname(rs.getString(4));
                lettore.setScuderiaPref(rs.getString(5));
                lettore.setModeratore(rs.getBoolean(6));
                if(rs.getDate(7) == null)
                    lettore.setDataFineSospensione(null);
                else
                    lettore.setDataFineSospensione(new java.util.Date(rs.getDate(7).getTime()));
                lettoreList.add(lettore);
            }
            return lettoreList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Resituisce tutti i lettori che non sono moderatori
     * @return la lista di tutti i lettori che non sono moderatori, la lista sarà vuota se non esistono lettori
     */
    public List<Lettore> doRetrieveAllNonModeratore() {
        List<Lettore> lettoreList = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT l.idLettore, l.email,l.pass, l.nickname, " +
                            "l.scuderiaPreferita, l.moderatore, l.dataFineSospensione" +
                            "  FROM formulaonlinedb.lettore l" +
                            " WHERE l.moderatore = 0");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lettore lettore = new Lettore();
                lettore.setIdLettore(rs.getInt(1));
                lettore.setEmail(rs.getString(2));
                lettore.setPassword(rs.getString(3));
                lettore.setNickname(rs.getString(4));
                lettore.setScuderiaPref(rs.getString(5));
                lettore.setModeratore(rs.getBoolean(6));
                if(rs.getDate(7) == null)
                    lettore.setDataFineSospensione(null);
                else
                    lettore.setDataFineSospensione(new java.util.Date(rs.getDate(7).getTime()));

                lettoreList.add(lettore);
            }
            return lettoreList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Lettore doSave(Lettore lettore) {
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
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            lettore.setIdLettore(id);
            return lettore;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Salva un nuovo lettore nel database
     * @param email del nuovo lettore
     * @param password
     * @param nickname
     * @param scuderiaPreferita
     * @return il lettore se l'inserimento è andato a buon fine
     */
    public Lettore doSave(String email, String password, String nickname, String scuderiaPreferita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.lettore (email, pass, nickname, scuderiaPreferita)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setString(4, scuderiaPreferita);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            Lettore lettore = new Lettore(id, email, password, nickname, scuderiaPreferita, false);
            lettore.setIdLettore(id);
            return lettore;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per poter aggiornare tutti i parametri di un lettore
     * @param idLettore
     * @param email
     * @param password
     * @param nickname
     * @param scuderiaPreferita
     * @param moderatore
     * @param dataFineSospensione
     */
    public void doUpdate(int idLettore, String email, String password, String nickname, String scuderiaPreferita,
                         Boolean moderatore, java.util.Date dataFineSospensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.lettore " +
                            " SET email = ?, pass = ?, nickname = ?, scuderiaPreferita = ?," +
                            " dataFineSospensione = ?, moderatore = ? " +
                            "  WHERE idLettore=? ");

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setString(4, scuderiaPreferita);
            if(dataFineSospensione == null)
                ps.setDate(5,null);
            else ps.setDate(5, new Date(dataFineSospensione.getTime()));

            ps.setBoolean(6, moderatore);
            ps.setInt(7, idLettore);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metodo per aggiornare un lettore, limitato ai parametri che il lettore può modificare in autonomia
     * @param idLettore
     * @param email
     * @param password
     * @param nickname
     * @param scuderiaPreferita
     */
    public void doUpdate(int idLettore, String email, String password, String nickname, String scuderiaPreferita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.lettore " +
                            " SET email = ?, pass = ?, nickname = ?, scuderiaPreferita = ? " +
                            " WHERE idLettore=? ");

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setString(4, scuderiaPreferita);
            ps.setInt(5, idLettore);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per eliminare un lettore partendo dal suo id
     * @param idLettore da eliminare
     */
    public void doDelete(int idLettore) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.lettore WHERE idLettore=?");
            ps.setInt(1, idLettore);
            CommentoDAO commentoDAO = new CommentoDAO();
            commentoDAO.doUpdateAutore(idLettore, 1);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Controlla che l'email ed il nickname non siano già utilizzati
     * @param email
     * @param nickname
     * @return true se almeno uno dei due parametri è già in uso da un lettore, false altrimenti
     */
    public boolean checkExists(String email, String nickname) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM formulaonlinedb.lettore WHERE email=? OR nickname=?");
            ps.setString(1, email);
            ps.setString(2, nickname);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
