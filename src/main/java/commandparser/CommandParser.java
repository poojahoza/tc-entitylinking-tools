package main.java.commandparser;

import com.beust.jcommander.JCommander;

/**
 * @author poojaoza
 **/
public class CommandParser {

    private  JCommander  parse = null;
    private RegisterCommands.CommandDBpediaEL dBpediaEL = null;
    private RegisterCommands.CommandHelp helpc = null;
    private String[] argslist = null;

    public CommandParser(String  ... args)
    {
        dBpediaEL = new RegisterCommands.CommandDBpediaEL();
        helpc = new RegisterCommands.CommandHelp();
        argslist = args;
        parse = createParser();
    }

    private JCommander createParser()
    {
        if(parse == null)
        {
            parse = JCommander.newBuilder().addCommand("dbpedia",dBpediaEL).addCommand("--help",helpc).build();
            parse.parse(argslist);
        }
        return parse;
    }

    public JCommander getParser()
    {
        return parse;
    }

    public RegisterCommands.CommandDBpediaEL getdBpediaELCommand() { return dBpediaEL; }
}
