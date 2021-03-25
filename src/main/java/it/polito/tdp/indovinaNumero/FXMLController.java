package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;

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
    	// iniio nuova partita
    	model.nuovaPartita();
    	
    	// gestione dell'interfaccia
    	this.txtRisultato.clear();
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX())) ;
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
    	
    	int result = 0 ;
    	try {
    		result = this.model.tentativo(tentativo) ;
		} catch (IllegalStateException se) {
			this.txtRisultato.setText(se.getMessage());
			this.layoutTentativo.setDisable(true);
			return ;
		}
    	catch (InvalidParameterException pe) {
    		this.txtRisultato.setText(pe.getMessage());
    		return ;
    	}
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));

    	
    	if(result == 0) {
    		//HO INDOVINATO
    		this.txtRisultato.setText("HAI VINTO CON "+ this.model.getTentativiFatti()+ " TENTATIVI");
    		this.layoutTentativo.setDisable(true) ;
    		return ;
    	} else if(result < 0){
    		txtRisultato.setText("Tentativo troppo basso");
    		return ;
    		
    	} else {
			txtRisultato.setText("Tentativo troppo alto !!");
		}
       	
    }
    
    public void setModel(Model model) {
    	this.model = model ;
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
