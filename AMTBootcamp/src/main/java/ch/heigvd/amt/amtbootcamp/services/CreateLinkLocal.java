/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.net.URI;
import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author basilechatillon
 */
@Local
public interface CreateLinkLocal {
    
    public String getAPIPath();
    
    public String getServletDisplayPath();
    public String getServletDeletePath();
    public String getServletCreatePath();
    public String getServletUpdatePath();
    public String getServletGeneratePath();
    
    public URI APICreateRandom(int number);
    
    public URI APIGet(int ID);
    public URI APIGet(DogDTO dog);
    
    public URI APIDelete(int id);
    public URI APIDelete(DogDTO dog);
    public List<URI> APIDelete(List<DogDTO> dogs);

    
    public URI APICustom();
    
    public URI APIUpdate(int ID);
    
    public List<String> createStringLinks(List<URI> uris);
    
    public URI ServletDelete(int id, int page, int entry);
    public URI ServletDelete(DogDTO dog, int page, int entry);
    public List<URI> ServletDelete(List<DogDTO> dogs, int page, int entry);
    
    public URI ServletDisplayPage(int pageNumber, int entry);
    public List<URI> ServletDisplayPage(int pageNumber, List<Integer> entries);
    
    public URI ServletUpdate(int id, int page, int entry);
    public URI ServletUpdate(DogDTO dog, int page, int entry);
    public List<URI> ServletUpdate(List<DogDTO> dogs, int page, int entry);
}
