package syl.study.lucene.suggest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试product Suggest 入口
 * Lucene 关键字提示测试
 *
 * @author 史彦磊
 * @create 2017-08-11 18:40.
 */
public class SuggentProductTest {

    private static void lookup(AnalyzingInfixSuggester suggester,String name,String region) throws IOException, ClassNotFoundException {
        Set<BytesRef> contexts = new HashSet<BytesRef>();
        contexts.add(new BytesRef(region));
        //先用contexts条件过滤 再以name为关键字进行筛选，根据weight 值排序返回前2条，
        //第三个boolean值即是否每个term都要匹配 第四个参数表示是否需要关键字高亮
        List<Lookup.LookupResult> results = suggester.lookup(name, contexts, 2, true, false);
        System.out.println("name: "+ name +", region: "+region);
        for (Lookup.LookupResult result : results) {
            System.out.println("key :　"+result.key);
            BytesRef payload = result.payload;
            ByteArrayInputStream bis = new ByteArrayInputStream(payload.bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object o = ois.readObject();
            Product product = (Product)o;
            System.out.println("product-Name:" + product.getName());
            System.out.println("product-regions:" + product.getRegions());
            System.out.println("product-image:" + product.getImage());
            System.out.println("product-numberSold:" + product.getSaleNum());
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RAMDirectory indexDir = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        AnalyzingInfixSuggester suggester = new AnalyzingInfixSuggester(indexDir,analyzer,analyzer,4,false);
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Electric Guitar",
                "http://images.example/electric-guitar.jpg", new String[] {
                "US", "CA" }, 100));
        products.add(new Product("Electric Train",
                "http://images.example/train.jpg", new String[] { "US",
                "CA" }, 100));
        products.add(new Product("Acoustic Guitar",
                "http://images.example/acoustic-guitar.jpg", new String[] {
                "US", "ZA" }, 180));
        products.add(new Product("Guarana Soda",
                "http://images.example/soda.jpg",
                new String[] { "ZA", "IE" }, 130));
        suggester.build(new ProductIterator(products.iterator()));

//        lookup(suggester,"Gu","US");
//        lookup(suggester,"Gu","ZA");
//        lookup(suggester, "Gui", "CA");
        lookup(suggester, "Electric guit", "US");



    }

}
