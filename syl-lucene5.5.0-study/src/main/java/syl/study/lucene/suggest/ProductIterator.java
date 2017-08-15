package syl.study.lucene.suggest;

import org.apache.lucene.search.suggest.InputIterator;
import org.apache.lucene.util.BytesRef;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 产品suggest 类
 *
 * @author 史彦磊
 * @create 2017-08-11 18:20.
 */
public class ProductIterator implements InputIterator {
    private Iterator<Product> productIterator;

    private Product currentProduct;

    ProductIterator(Iterator<Product> productIterator){
        this.productIterator = productIterator;
    }


    public long weight() {
        return this.currentProduct.getSaleNum();
    }

    /**
     * 将product对象序列化为payload
     * [这里仅仅是个示例，其实这种做法不可取,一般不会把整个对象存入payload,这样索引体积会很大，浪费硬盘空间]
     * @return
     */
    public BytesRef payload() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(currentProduct);
            out.close();
            return new BytesRef(bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasPayloads() {
        return true;
    }

    public Set<BytesRef> contexts() {
        Set<BytesRef> regions = new HashSet<BytesRef>();
        for (String region : currentProduct.getRegions()) {
            regions.add(new BytesRef(region));
        }
        return regions;
    }

    public boolean hasContexts() {
        return true;
    }

    public BytesRef next() throws IOException {
        if (productIterator.hasNext()){
            currentProduct = productIterator.next();
            //返回当前product的name值，把product类的name属性值作为key
            return new BytesRef(currentProduct.getName());
        }
        return null;
    }


}
