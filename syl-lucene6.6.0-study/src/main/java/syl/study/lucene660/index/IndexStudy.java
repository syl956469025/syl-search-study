package syl.study.lucene660.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 学习创建索引
 *
 * @author 史彦磊
 * @create 2017-07-16 12:51
 */
public class IndexStudy {


    private static final String INDEX_PATH = "D:/var/log/index";


    public static void main(String[] args) throws IOException {
        FSDirectory dir = new SimpleFSDirectory(Paths.get(INDEX_PATH));
        Document doc = new Document();



    }






}
