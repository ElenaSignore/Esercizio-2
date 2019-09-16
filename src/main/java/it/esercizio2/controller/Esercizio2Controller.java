/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.esercizio2.controller;

import it.esercizio2.connector.DbConnector;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Utente
 */
@Controller
public class Esercizio2Controller {
    
    @GetMapping("/")
    public String main(Model model){
        return "citta";
       
    }
    
    @GetMapping("/modificaCitta")
    public String modificaCitta(
    @RequestParam(name="nome", required=true,defaultValue="")String nome, Model model){
        String nomeCitta= nome;
        ArrayList<String> citta=DbConnector.getCitta(nome);
        model.addAttribute("cittaSelezionata", nomeCitta);
        model.addAttribute("distrettoSelezionato",DbConnector.getDistrict());
        model.addAttribute("popolazioneselezionata",DbConnector.getPopulation());
        model.addAttribute("modCitta", citta);
        return "modificaCitta";
    }
    
    @GetMapping("/risultato")
    public String risultato(
            @RequestParam(name="nome", required=true,defaultValue="")String nome,
            @RequestParam(name="distretto", required=true,defaultValue="")String distretto,
            @RequestParam(name="popolazione", required=true,defaultValue="")String popolazione,
            Model model){
           
            int popolazioneUp= Integer.parseInt(popolazione);
            
            DbConnector.update(nome, distretto, popolazioneUp);
        
            return "risultato";
        }
    

    
}
