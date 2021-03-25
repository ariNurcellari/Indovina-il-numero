package it.polito.tdp.indovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX = 100 ;
	private final int TMAX = 8 ;
	private int segreto;
	private int tentativiFatti ;
	private boolean inGioco = false ;
	Set<Integer> tentativi ;
	
	public void nuovaPartita() {
		// gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1 ;
    	this.tentativiFatti = 0;
    	this.inGioco = true ;
    	this.tentativi = new HashSet<Integer>() ;
	}
	
	public int tentativo(int tentativo){
		// controllo se partita è in corso 
		if(!inGioco) {
			throw new IllegalStateException("HAI PERSO IL SEGRETO ERA: !" + this.segreto) ;
		}
		// controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e NMAX, e non puoi inserire lo stesso numero!!") ;
		}
		// il tentativo è valido 
		this.tentativiFatti ++ ;
		this.tentativi.add(tentativo) ;
		
		if(this.tentativiFatti == (TMAX-1))
			this.inGioco = false ;
		if(tentativo  == segreto) {
			this.inGioco = false ;
			return 0 ;
		} else if(tentativo < this.segreto) {
			return -1 ;
		} else 
			return 1 ;

	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX) 
			return false ;
		if(tentativi.contains(tentativo))
			return false ;
		return true ;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

}
