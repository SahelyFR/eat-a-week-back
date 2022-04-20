package com.sahelyfr.eataweekback.controller;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahelyfr.eataweekback.model.Recette;
import com.sahelyfr.eataweekback.service.RecetteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecetteController {
    
    @Autowired
    private RecetteService rs;

    @GetMapping("/recettes")
    public Iterable<Recette> getAllRecettes(){
        return rs.getAllRecettes();
    }

    @GetMapping("/recettes/{season}")
    public Iterable<Recette> getRecettesBySeason(@PathVariable("season") String season){
        return rs.getRecettesBySeason(season);
    }

    @GetMapping("/recette/{id}")
    public Recette getRecette(@PathVariable("id") final Long id){
        Optional<Recette> recette = rs.getRecette(id);
        if(recette.isPresent()){
            return recette.get();
        }else{
            return null;
        }
    }

    @PutMapping("/recette/{id}")
    public Recette updateRecette(@PathVariable("id") final Long id, @RequestBody Recette recette){
        Optional<Recette> re = rs.getRecette(id);
        if(re.isPresent()){
            Recette currentRecette = re.get();
            
            String name = recette.getName();
            if(name != null){
                currentRecette.setName(name);
            }
            String link = recette.getLink();
            if(link != null){
                currentRecette.setLink(link);
            }
            currentRecette.setSpring(recette.isSpring());
            currentRecette.setSummer(recette.isSummer());
            currentRecette.setAutumn(recette.isAutumn());
            currentRecette.setWinter(recette.isWinter());

            rs.saveRecette(currentRecette);
            return currentRecette;
        }else{
            return null;
        }
    }

    @PostMapping(path="/recette", consumes=MediaType.APPLICATION_JSON_VALUE)
    public Recette createRecette(@RequestBody String payload) throws JsonMappingException, JsonProcessingException{
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();

        Recette recette = mapper.readValue(payload, Recette.class);
        
        if(recette.getName() != null && recette.getLink() != null){
            rs.saveRecette(recette);
            return recette;
        }else{
            return null;
        }
    }

    @DeleteMapping("/recette/{id}")
    public void deleteRecette(@PathVariable("id") final Long id){
        rs.deleteRecette(id);
    }
}
