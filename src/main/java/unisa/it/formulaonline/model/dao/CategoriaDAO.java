package unisa.it.formulaonline.model.dao;

import unisa.it.formulaonline.model.entity.Categoria;
import unisa.it.formulaonline.model.entity.Lettore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private LettoreDAO lettoreDAO;

    public Categoria doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome,c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM formulaonlinedb.categoria c  " +
                            " WHERE c.idCategoria=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.getInt(1);
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);
                lettoreDAO = new LettoreDAO();
                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);

                return categoria;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> doRetrieveAll() {
        List<Categoria> categoriaList = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.idCategoria, c.nome,c.descrizione, c.categoriaPadre, c.creatore" +
                            "  FROM  formulaonlinedb.categoria c");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNome(rs.getString(2));
                categoria.setDescrizione(rs.getString(3));

                Categoria categoriaPadre = doRetrieveById(rs.getInt(4));
                categoria.setCategoriaPadre(categoriaPadre);

                lettoreDAO = new LettoreDAO();
                Lettore lettore = lettoreDAO.doRetrieveById(rs.getInt(5));
                categoria.setCreatore(lettore);
                categoriaList.add(categoria);
            }
            return categoriaList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Categoria doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO formulaonlinedb.categoria (nome, descrizione, categoriaPadre, creatore)" +
                            "  VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescrizione());
            ps.setInt(3, categoria.getCategoriaPadre().getIdCategoria());
            ps.setInt(4, categoria.getCreatore().getIdLettore());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        return categoria;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doUpdate(Categoria categoria, int idCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.categoria "+
                            " SET creatore = ?, categoriaPadre = ? , nome = ? ,descrizione = ? " +
                            "  WHERE idCategoria=? ");

            ps.setInt(1, categoria.getCreatore().getIdLettore());
            ps.setInt(2, categoria.getCategoriaPadre().getIdCategoria());
            ps.setString(3, categoria.getNome());
            ps.setString(4, categoria.getDescrizione());
            ps.setInt(5, idCategoria);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            return categoria;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * assegna alle categorie individuate da categoria padre la categoria "senza categoria"
     * @param idCategoriaPadre
     */
    public void doUpdateByCategoriaPadre(int idCategoriaPadre) {

        Categoria no_categoria = doRetrieveById(1) ;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    " UPDATE formulaonlinedb.categoria "+
                            " SET categoriaPadre = ? " +
                            "  WHERE categoriaPadre=? ");

            ps.setInt(1, no_categoria.getIdCategoria());
            ps.setInt(2, idCategoriaPadre);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  elimina la categoira selezionata e le sottocategorie
     * @param idCategoria
     */
    public void doDelete(int idCategoria){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.categoria WHERE idCategoria=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCategoria);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *  elimina la categoira selezionata e assegna alle sottocategorie "senza categoria padre"
     * @param idCategoria
     */
    public void doDeleteAlternativo(int idCategoria){

        doUpdateByCategoriaPadre(idCategoria);
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM formulaonlinedb.categoria WHERE idCategoria=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCategoria);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
