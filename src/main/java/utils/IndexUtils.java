package main.java.utils;

import edu.unh.cs.treccar_v2.Data;
import edu.unh.cs.treccar_v2.read_data.DeserializeData;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author poojaoza
 **/
public class IndexUtils {

    public static Iterable<Data.Paragraph> createParagraphIterator(String cborLoc) {

        File file = new File(cborLoc);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return DeserializeData.iterableParagraphs(inputStream);
    }

    public static IndexWriter createIndexWriter(String indexLoc) throws IOException {

        Path indexPath = Paths.get(indexLoc);
        try {
            FSDirectory indexDir = FSDirectory.open(indexPath);
            IndexWriterConfig conf = new IndexWriterConfig(new EnglishAnalyzer());
            conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            return new IndexWriter(indexDir, conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
