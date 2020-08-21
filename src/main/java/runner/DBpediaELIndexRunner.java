package main.java.runner;

import main.java.commandparser.CommandParser;
import main.java.commandparser.RegisterCommands;
import main.java.commandparser.ValidateCommands;
import main.java.indexer.DBpediaIndexBuilder;

import java.io.IOException;


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

        String destFinalPath = dBpediaELParser.getDestpath()+"_paragraph";
        DBpediaIndexBuilder dBpediaIndexBuilder;

        try {
            dBpediaIndexBuilder = new DBpediaIndexBuilder(destFinalPath, dBpediaELParser.getIndexPath());
            dBpediaIndexBuilder.createDBpediaIndex();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

}
