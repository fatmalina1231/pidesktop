/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackholetech.fxnotification;

/**
 *
 * @author AnWaR
 */
public class Notification {

    
    private String title; //required
    private String message; //required
    private int type;   //required
    private int position;   //optional

    public Notification(NotificationBuilder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.type = builder.type;
    }
    
    public static class NotificationBuilder {
        private String title; //required
        private String message; //required
        private int type;   //required
        private int position;   //optional

        public NotificationBuilder(String title, String message, int type) {
            this.title = title;
            this.message = message;
            this.type = type;
        }
        
        public NotificationBuilder position(int position){
            this.position = position;
            return this;
        }
        
        //build methods
        public Notification build(){
            return new Notification(this);
        }
        
    }
    
}
