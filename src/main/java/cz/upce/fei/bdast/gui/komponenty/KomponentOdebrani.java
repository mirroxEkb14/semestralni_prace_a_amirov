package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.gui.kontejnery.MrizkovyPanel;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.gui.kontejnery.Tlacitko;
import cz.upce.fei.bdast.gui.koreny.SeznamPanel;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu {@link TitledPane} s
 * tlačítky ({@link Button}) pro odebrání prvků seznamu
 * <p>
 * Třída je Singleton
 */
public final class KomponentOdebrani extends TitulkovyPanel {

    /**
     * Deklarace tlačítek
     */
    private final Button btnOdeberPrvni, btnOdeberPosledni,
            btnOdeberNaslednika, btnOdeberPredchudce, btnOdeberAktualni;

    private static KomponentOdebrani instance;

    /**
     * Tovární metoda (factory method) vracející existující nebo nově vytvořenou instanci
     * dané třídy
     */
    public static KomponentOdebrani getInstance() {
        if (instance == null)
            instance = new KomponentOdebrani();
        return instance;
    }

    /**
     * Konstruktor inicializuje veškerá tlačítka a titulky
     */
    private KomponentOdebrani() {
        this.btnOdeberPrvni = new Tlacitko(
                Titulek.PRVNI.getNadpis());
        this.btnOdeberPrvni.setDisable(true);
        this.btnOdeberPrvni.setOnAction(actionEvent -> nastavUdalostOdeberPrvni());

        this.btnOdeberPosledni = new Tlacitko(
                Titulek.POSLEDNI.getNadpis());
        this.btnOdeberPosledni.setDisable(true);

        this.btnOdeberNaslednika = new Tlacitko(
                Titulek.NASLEDNIK.getNadpis());
        this.btnOdeberNaslednika.setDisable(true);

        this.btnOdeberPredchudce = new Tlacitko(
                Titulek.PREDCHUDCE.getNadpis());
        this.btnOdeberPredchudce.setDisable(true);

        this.btnOdeberAktualni = new Tlacitko(
                Titulek.AKTUALNI.getNadpis());
        this.btnOdeberAktualni.setDisable(true);

        this.btnOdeberAktualni.setPrefWidth(MrizkovyPanel.PREFEROVANA_SIRKA_VELKEHO_TLACITKA);

        nastavKomponentOdebrani();
    }

    private void nastavKomponentOdebrani() {
        this.setText(Titulek.KOMPONENT_ODEBRANI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(btnOdeberPrvni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnOdeberPosledni, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(btnOdeberNaslednika, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnOdeberPredchudce, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(btnOdeberAktualni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_TRETI);
        GridPane.setColumnSpan(btnOdeberAktualni, MrizkovyPanel.ROZPETI_SLOUPCU);
        return gridPane;
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Vastaví událost (action), která se provede po stisknutí tlačítka {@link Titulek#PRVNI}
     */
    private void nastavUdalostOdeberPrvni() {
        final boolean bylAktualniPrvnim = SeznamPanel.getInstance().smazPrvni();
        if (bylAktualniPrvnim) {
            if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozNaslednika())
                KomponentVlozeni.getInstance().vypniBtnVlozNaslednika();
            if (!KomponentVlozeni.getInstance().jeVypnutoBtnVlozPredchudce())
                KomponentVlozeni.getInstance().vypniBtnVlozPredchudce();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniNaslednika())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniNaslednika();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
            if (!KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniAktualni())
                KomponentZpristupnovani.getInstance().vypniBtnZpristupniAktualni();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberNaslednika())
                KomponentOdebrani.getInstance().vypniBtnOdeberNaslednika();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce())
                KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
            if (!KomponentOdebrani.getInstance().jeVypnutoBtnOdeberAktualni())
                KomponentOdebrani.getInstance().vypniBtnOdeberAktualni();
        }
        if (SeznamPanel.getInstance().jePrazdny()) {
            vypniBtnOdeberPrvni();
            vypniBtnOdeberPosledni();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPrvni();
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPosledni();
        }
        if (!SeznamPanel.getInstance().jeIndexPlatnyProPredchudce() &&
                !KomponentZpristupnovani.getInstance().jeVypnutoBtnZpristupniPredchudce()) {
            KomponentZpristupnovani.getInstance().vypniBtnZpristupniPredchudce();
        }
        if (!SeznamPanel.getInstance().jeIndexPlatnyProPredchudce() &&
                !KomponentOdebrani.getInstance().jeVypnutoBtnOdeberPredchudce()) {
            KomponentOdebrani.getInstance().vypniBtnOdeberPredchudce();
        }
    }

    public boolean jeVypnutoBtnOdeberPrvni() { return btnOdeberPrvni.isDisabled(); }

    public boolean jeVypnutoBtnOdeberPosledni() { return btnOdeberPosledni.isDisabled(); }

    public boolean jeVypnutoBtnOdeberNaslednika() { return btnOdeberNaslednika.isDisabled(); }

    public boolean jeVypnutoBtnOdeberPredchudce() { return btnOdeberPredchudce.isDisabled(); }

    public boolean jeVypnutoBtnOdeberAktualni() { return btnOdeberAktualni.isDisabled(); }


// <editor-fold defaultstate="collapsed" desc="Přepínače">
    public void zapniBtnOdeberPrvni() { btnOdeberPrvni.setDisable(false); }

    public void vypniBtnOdeberPrvni() { btnOdeberPrvni.setDisable(true); }

    public void zapniBtnOdeberPosledni() { btnOdeberPosledni.setDisable(false); }

    public void vypniBtnOdeberPosledni() { btnOdeberPosledni.setDisable(true); }

    public void zapniBtnOdeberNaslednika() { btnOdeberNaslednika.setDisable(false); }

    public void vypniBtnOdeberNaslednika() { btnOdeberNaslednika.setDisable(true); }

    public void zapniBtnOdeberPredchudce() { btnOdeberPredchudce.setDisable(false); }

    public void vypniBtnOdeberPredchudce() { btnOdeberPredchudce.setDisable(true); }

    public void zapniBtnOdeberAktualni() { btnOdeberAktualni.setDisable(false); }

    public void vypniBtnOdeberAktualni() { btnOdeberAktualni.setDisable(true); }
// </editor-fold>
}
