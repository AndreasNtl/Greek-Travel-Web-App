/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import entities.Role;
import entities.User;

/**
 *
 * @author andreas
 */
public class RoleHelper {
    public static boolean isAdministrator(User user) {
        for (Role r : user.getRoleList()) {
            if (r.getId() == 1) {
                return true;
            }
        }
        return false;
    }
    public static boolean isGuest(User user) {
        if (user == null || user.getRoleList() == null || user.getRoleList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isOwner(User user) {
        for (Role r : user.getRoleList()) {
            if (r.getId() == 2) {
                return true;
            }
        }
        return false;
    }
    public static boolean isVisitor(User user) {
        for (Role r : user.getRoleList()) {
            if (r.getId() == 3) {
                return true;
            }
        }
        return false;
    }
}
