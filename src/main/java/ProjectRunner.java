package main.java;

import main.java.commandparser.CommandParser;
import main.java.runner.*;

/**
 * @author poojaoza
 **/
public class ProjectRunner {

    public static void main(String[] args)
    {
        /*
          Parses the command line and creates the parser
        */
        CommandParser parser = new CommandParser(args);
        ProgramRunner runner = null;
        if(args.length < 1)
        {
            parser.getParser().usage();
        }
        else {
            if (parser.getParser().getParsedCommand().equals("dbpedia")) {
                runner = new DBpediaELIndexRunner(parser);
                runner.run();
            }else {
                parser.getParser().usage();
            }
        }
    }
}
