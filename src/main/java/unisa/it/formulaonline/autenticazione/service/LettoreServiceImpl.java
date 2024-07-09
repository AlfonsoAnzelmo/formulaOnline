package unisa.it.formulaonline.autenticazione.service;

import unisa.it.formulaonline.model.dao.LettoreDAO;
import unisa.it.formulaonline.model.entity.Lettore;

import java.util.Date;

public class LettoreServiceImpl implements LettoreService {


    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore nominaModeratore(int idLettore) {
        LettoreDAO lettoreDAO = new LettoreDAO();

        Lettore lettore = lettoreDAO.doRetrieveById(idLettore);
        lettoreDAO.doUpdate(lettore.getIdLettore(), lettore.getEmail(), lettore.getPassword(),
                lettore.getNickname(), lettore.getScuderiaPref(), Boolean.TRUE, lettore.getDataFineSospensione());
        lettore.setModeratore(Boolean.TRUE);
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lettore aggiornaLettore(int idLettore, String email, String password, String nickname,
                                   String scuderiaPreferita, Boolean moderatore, Date dataFineSospensione) {

        LettoreDAO lettoreDAO = new LettoreDAO();

        lettoreDAO.doUpdate(idLettore, email, password, nickname, scuderiaPreferita, moderatore, dataFineSospensione);
        Lettore lettore = new Lettore(idLettore, email, password, nickname, scuderiaPreferita, moderatore, dataFineSospensione);
        return lettore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminaLettore(int idLettore) {
        LettoreDAO lettoreDAO = new LettoreDAO();

        lettoreDAO.doDelete(idLettore);
    }

    @Override
    public Lettore ottieniLettoreDaId(int idLettore) {

        LettoreDAO lettoreDAO = new LettoreDAO();
        return lettoreDAO.doRetrieveById(idLettore);
    }
}
