package main.java.indexer;

import edu.unh.cs.treccar_v2.Data;
import main.java.utils.IndexUtils;
import main.java.entitylinker.DBpediaEntityLinker;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

/**
 * @author poojaoza
 **/
public class DBpediaIndexBuilder {

    private IndexWriter indexWriter;
    private static int increment=0;
    private String cborLoc;
    private HttpClient client;
    private DBpediaEntityLinker dBpediaEntityLinker;

    public DBpediaIndexBuilder(String indexDir, String cborFileLoc) throws IOException {
        indexWriter = IndexUtils.createIndexWriter(indexDir);
        cborLoc=cborFileLoc;
        SimpleHttpConnectionManager connManager=new SimpleHttpConnectionManager(true);
        client=new HttpClient(connManager);
        dBpediaEntityLinker = new DBpediaEntityLinker();
    }

    private void parseParagraphs(){

        Iterable<Data.Paragraph> paragraphIterable = IndexUtils.createParagraphIterator(cborLoc);

        try
        {
        for(Data.Paragraph para: paragraphIterable){

            Document document = new Document();

            FieldType contentType = new FieldType();
            contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
            contentType.setStored(true);
            contentType.setTokenized(true);
            contentType.setStoreTermVectors(true);

            String paraText = para.getTextOnly();
            String paraEntities = dBpediaEntityLinker.getEntities(paraText,client);

            //Then we add the paragraph id and the paragraph body for searching
            document.add(new StringField("Id", para.getParaId(), Field.Store.YES));
            document.add(new Field("Text", paraText, contentType));
            document.add(new StringField("EntityLinks", paraEntities, Field.Store.YES));

            try {
                indexWriter.addDocument(document);
                increment++;

                //commit the Data after 50 paragraph

                if(increment % 20000 ==0)
                {
                    indexWriter.commit();
                }
                System.out.println(increment);
            }catch(IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            ((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
            closeIndexWriter();
        }

    }

    private void closeIndexWriter()
    {
        if (indexWriter != null)
        {
            try
            {
                indexWriter.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

    public void createDBpediaIndex(){
        parseParagraphs();
    }
}
