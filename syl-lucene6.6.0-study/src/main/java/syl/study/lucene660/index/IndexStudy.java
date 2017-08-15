package syl.study.lucene660.index;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * 学习创建索引
 *
 * @author 史彦磊
 * @create 2017-07-16 12:51
 */
public class IndexStudy {





    public static void main(String[] args) throws IOException {
        //创建索引
//        writeIndex();
        //查询索引
        IndexReader reader = DirectoryReader.open(LuceneUtil.getFsDirectory(null));
        IndexSearcher searcher = new IndexSearcher(reader);
        TermQuery query = new TermQuery(new Term("userName","zhangsan"));
        TopDocs search = searcher.search(query, 10);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        System.out.println(scoreDocs.length);
        Document doc = searcher.doc(scoreDocs[0].doc);
        String userName = doc.get("userName");
        System.out.println(userName);


    }


    private static void writeIndex()throws IOException{
        Directory dir = LuceneUtil.getFsDirectory(null);
        index(dir);
    }

    private static void index(Directory dir) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("userName","zhangsan", Field.Store.YES));
        doc.add(new LongPoint("id",1));
        doc.add(new TextField("content","我是一个小学生,我爱妈妈", Field.Store.YES));
        IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(dir,conf);
        writer.addDocument(doc);
        writer.commit();
    }




}
