package jit.wxs.search.mapper;

import jit.wxs.common.pojo.SearchItem;

import java.util.List;

/**
 * Solr 商品索引DAO
 * @author jitwxs
 * @date 2018/4/11 22:06
 */
public interface SearchItemMapper {

    List<SearchItem> listSearchItem();

    SearchItem getById(Long id);
}
