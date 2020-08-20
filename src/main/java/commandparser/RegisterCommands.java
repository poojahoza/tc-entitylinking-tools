package main.java.commandparser;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author poojaoza
 **/
public class RegisterCommands {

    @Parameters(separators = "=",commandDescription = "Command to Index the Corpus")
    public static class CommandDBpediaEL
    {

        @Parameter(names = {"-i","--corpus-file"},description = "Corpus file to index",required=true)
        private String IndexPath;

        @Parameter(names = {"-d","--dest-location"},description = "Location to save the index file")
        private String destpath = System.getProperty("user.dir") + System.getProperty("file.separator") + "para.dbpedia.lucene.eng";


        @Parameter(names = "--help", help = true)
        private boolean help;

        boolean isHelp()
        {
            return help;
        }

        public String getIndexPath()
        {
            return IndexPath;
        }

        public String getDestpath()
        {
            return destpath;
        }

    }

    @Parameters(separators = "=", commandDescription = "Help Information")
    public static class CommandHelp {

    }
}
