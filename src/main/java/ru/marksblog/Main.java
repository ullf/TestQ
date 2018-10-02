package ru.marksblog;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File f = null;
        try {
            f = new File(args[0]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Type filepath to your property file as a first parameter.");
        }
        if(!f.exists()){
            System.out.println("File doesn't exists!");
        }
        Config config=new Config(args[0]);
        Api api=new Api();
        api.setup();
        api.login(config.getEmail(),config.getPassword());
        api.writeMessage(config.getToWho(),config.getSubject(),config.getMessage());
    }
}
