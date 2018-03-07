/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Role;
import java.util.List;

/**
 *
 * @author psilos
 */
public interface RoleDAO {
   public List <Role> list();
   
   public void create(Role r);
   
   public Role findById(int id);
}
