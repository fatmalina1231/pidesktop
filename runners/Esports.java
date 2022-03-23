/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runners;

import model.User;
import interfaces.IUser;
import interfaces.IAdmin;
import java.sql.SQLException;
import services.AdminService;


public class Esports {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception  {
        // TODO code application logic here
        
        //Personne
        User u1 = new User("dem1", "dem1", "dem1@dem.com","ProfilePic", "dem","client",1);
        User u2 = new User("adm", "admin", "admin@admin.com","ProfilePic", "adm","admin",0);

        //Interface
        
        IUser Iu = new UserService();
        IAdmin Ia = new AdminService ();
      
           

            
}}
