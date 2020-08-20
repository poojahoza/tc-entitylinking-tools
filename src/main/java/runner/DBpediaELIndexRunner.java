package main.java.runner;

import main.java.commandparser.CommandParser;
import main.java.commandparser.RegisterCommands;
import main.java.commandparser.ValidateCommands;


/**
 * @author poojaoza
 **/
public class DBpediaELIndexRunner implements ProgramRunner{

    private RegisterCommands.CommandDBpediaEL dBpediaELParser = null;
    private ValidateCommands.ValidateDBpediaELCommands validate = null;

    public DBpediaELIndexRunner(CommandParser parser)
    {
        dBpediaELParser = parser.getdBpediaELCommand();
        validate = new ValidateCommands.ValidateDBpediaELCommands(dBpediaELParser);
    }

    @Override
    public void run(){
        validate.ValidateDBpediaEL();

    }

}
