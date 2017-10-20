/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import javax.ejb.Local;

/**
 *
 * @author basilechatillon
 */
@Local
public interface DeleteDogLocal {
    public boolean deleteDog(int id);    
}
