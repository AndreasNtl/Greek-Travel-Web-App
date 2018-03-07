/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Availability;
import java.util.List;

/**
 *
 * @author psilos
 */
public interface AvailabilityDAO {
    List <Availability> list();
    public void create(Availability a);
    public Availability findAvailabilityById(int id);
}
