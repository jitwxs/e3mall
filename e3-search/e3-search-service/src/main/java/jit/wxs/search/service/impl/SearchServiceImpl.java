package jit.wxs.search.service.impl;

import jit.wxs.common.pojo.SearchItemResult;
import jit.wxs.search.dao.SearchDao;
import jit.wxs.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jitwxs
 * @date 2018/4/12 0:27
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchItemResult search(String keywords, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();

        //设置搜索默认域和关键字
        query.set("df","item_title");
        query.set("q",keywords);

        //设置分页
        if(page <= 0) {
            page = 1;
        }
        query.setStart((page-1)*rows);
        query.setRows(rows);

        // 设置高亮
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style='color:red'>");
        query.setHighlightSimplePost("</em>");

        SearchItemResult result = searchDao.searchItem(query);

        int totalPages = (int) (result.getRecordCount() / rows);
        if(result.getRecordCount() % rows != 0) {
            totalPages++;
        }
        result.setTotalPages(totalPages);

        return result;
    }
}
