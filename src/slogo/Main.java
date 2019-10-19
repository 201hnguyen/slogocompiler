package slogo;

import slogo.backend.commands.control.Repeat;
import slogo.backend.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static final String WHITESPACE = "\\s+";
    private String[] examples = {
            "",
            "# foo",
            "foo #",
            "#",
            "fd",
            "FD",
            "forwardd",
            "equalp",
            "equal?",
            "equal??",
            "+",
            "SuM",
            "-",
            "*",
            "/",
            "%",
            "~",
            "+not",
            "not+",
            "++",
            "+*+",
            "or",
            "FOR",
            "allOrNothing",
            "all_or_nothing",
            "allOr_nothing?",
            "allOr?nothing_",
            ":allornothing",
            "PI",
            "90",
            "9.09",
            "9.0.0",
            "[",
            "]",
            "(",
            ")"
    };

    //TODO:  something like this will come from visualization.java which will go to connector.java

    /**
     * utility function that reads given file and returns its entire contents as a single string
     *
     * @param input
     * @return
     */
    private String readFileToString(URI input) {
        Path p = Paths.get(input);
        try {
            return new String(Files.readAllBytes(p));
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read input file.");
            return "";
        }
    }

    //TODO:  move this to parser.java

    /**
     * given some text, prints results of parsing it using the given language
     */
    private void parseText(parser lang, String[] text) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            }
        }
        System.out.println();
    }


    public static void main(String[] args) throws URISyntaxException {

        //TODO: decide what I want in main.java and parser.java
        //from spike_slogo code @author Duvall
        Main m = new Main(); //why do you do this?

        // set up the parser
        parser lang = new parser();
        // these are more specific, so add them first to ensure they are checked first
        lang.addPatterns("English");
        lang.addPatterns("Syntax");

        // note, this simple "algorithm" will not handle SLogo comments
        // try against different kinds of inputs
        m.parseText(lang, m.examples);
        String userInput = "fd 50 rt 90 BACK :distance Left :angle";
        m.parseText(lang, userInput.split(WHITESPACE));
        String fileInput = m.readFileToString(Main.class.getClassLoader().getResource("square.logo").toURI());
        m.parseText(lang, fileInput.split(WHITESPACE));

        System.out.println("test");
        //from master
        // RepeatableCommandBlock commandStructure = new RepeatableCommandBlock("repeat 180 [ fd 5 rt 2 ]");

        Repeat repeat = new Repeat();
        List<Object> parameters = new ArrayList<>();
        parameters.add(3);
        parameters.add("fd 50 fd 10"); // FIXME: currently split by comma just for sake of demo
        repeat.execute(parameters);
    }


}
