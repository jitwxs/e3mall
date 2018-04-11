package jit.wxs.search.service;

import jit.wxs.common.pojo.SearchItemResult;

/**
 * @author jitwxs
 * @date 2018/4/12 0:27
 */
public interface SearchService {

    SearchItemResult search(String keywords, int page, int rows) throws Exception;
}
