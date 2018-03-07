/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Location;
import java.util.List;

/**
 *
 * @author psilos
 */
public interface LocationDAO {
    List <Location> list();
    public void create(Location l);
    public Location findLocationById(int id);
    public Location findLocationByTheRoomId(int roomId);
}
