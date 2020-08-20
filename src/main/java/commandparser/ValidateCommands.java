package main.java.commandparser;

/**
 * @author poojaoza
 **/
public class ValidateCommands {

    public static class ValidateDBpediaELCommands
    {
        RegisterCommands.CommandDBpediaEL indexParser=null;
        public ValidateDBpediaELCommands(RegisterCommands.CommandDBpediaEL indexParser)
        {
            this.indexParser = indexParser;
        }

        private void CALLEXIT(int status)
        {
            System.exit(status);
        }

        public void ValidateDBpediaEL()
        {
            if(indexParser.getIndexPath()==null)
            {
                CALLEXIT(-1);
            }
        }


    }
}
