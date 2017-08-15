package syl.study.lucene660.index;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import syl.study.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 学习Lucene工具类
 *
 * @author 史彦磊
 * @create 2017-07-21 15:08.
 */
public class LuceneUtil {

    private static final String INDEX_PATH = "D:/var/log/index";

    /**
     * 获取磁盘上的索引目录
     * @return
     * @param path
     * @throws IOException
     */
    public static Directory getFsDirectory(String path) throws IOException {
        String indexPath ;
        if (StringUtils.isEmpty(path)){
            indexPath = path;
        }else {
            indexPath = INDEX_PATH;
        }
        return new SimpleFSDirectory(Paths.get(indexPath));
    }




}
