package syl.study.lucene.suggest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 产品类
 *
 * @author 史彦磊
 * @create 2017-08-11 18:11.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    /** 产品名称 **/
    private String name;

    /** 产品图片 **/
    private String image;

    /** 产品销售区域 **/
    private String[] regions;

    /** 销量 **/
    private int saleNum;


}
