package slogo;

import slogo.backend.Parser;

import java.util.ResourceBundle;

public class Main {

    private static String test_input1 = "";
    private static String test_input2 = "fd 50";
    private static String test_input3 = "repeat 2 [ fd 50 ]";
    private static String myLanguage = "English";

    public static void main(String[] args) {

        Parser p = new Parser(myLanguage);
        String toBackend = p.translateMyCommands(test_input3, myLanguage);
        System.out.println(toBackend);


//        //test Parser.splitInput
////      System.out.println(test_input2);
////      String[] test = Parser.splitInput(test_input2);
////      for (int i = 0; i < test.length; i++) {
////          System.out.println(test[i]);
////      }
//
//        //test Parser.getCommandType
////        Parser.addPatterns(myLanguage);
////        String test = Parser.getCommandType("fd");
////        System.out.println(test);
//
//        //test Parser.getTranslatedCommands()
//        String cmd_translated = Parser.getTranslatedCommand(ResourceBundle.getBundle("resources.languages/" + myLanguage), "fd");
//
//        //String cmd_translated = Parser.getTranslatedCommand(ResourceBundle.getBundle("resources.languages/" + myLanguage), "5");
//        System.out.println(cmd_translated);


    }

}