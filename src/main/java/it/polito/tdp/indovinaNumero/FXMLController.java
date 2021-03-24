package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100 ;
	private final int TMAX = 8 ;
	private int segreto;
	private int tentativiFatti ;
	private boolean inGioco = false ;

	@FXML
    private HBox layoutTentativo;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtTentativi;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	// gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1 ;
    	this.tentativiFatti = 0;
    	this.inGioco = true ;
    	
    	// gestione dell'interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX)) ;
    	this.layoutTentativo.setDisable(false);

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//lettura input dell'utente
    	String ts = this.txtTentativoUtente.getText() ;
    	int tentativo ;
    	try {
    		tentativo = Integer.parseInt(ts) ;
    		
    	}catch(NumberFormatException e){
    		this.txtRisultato.setText("Devi inserire un numero!");
    		return ;
    	}
    	
    	this.txtTentativoUtente.setText("");
    	this.tentativiFatti ++ ;
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti));
    	
    	if(tentativo == this.segreto) {
    		//HO INDOVINATO
    		this.txtRisultato.setText("HAI VINTO CON "+ this.tentativiFatti+ " TENTATIVI");
    		this.inGioco = false ;
    		this.layoutTentativo.setDisable(true) ;
    		return ;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		// ho esaurito i tentativi
    		this.txtRisultato.setText("HAI PERSO. IL SEGRETO ERA: " + this.segreto);
    		this.layoutTentativo.setDisable(true);
    		return ;
    		
    	}
    	
    	// Non ho vinto -> devo informare l'utente circa la bontà del suo tentativo
    	if(tentativo < this.segreto) {
    		this.txtRisultato.setText("TENTATIVO TROPPO BASSO");
    	}else {
    		this.txtRisultato.setText("TENTATIVO TROPPO ALTO");
    	}
    	    	
    }

    @FXML
    void initialize() {
    	 assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
