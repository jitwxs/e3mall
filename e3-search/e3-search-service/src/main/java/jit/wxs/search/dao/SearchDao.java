package jit.wxs.search.dao;

import jit.wxs.common.pojo.SearchItem;
import jit.wxs.common.pojo.SearchItemResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 索引搜索DAO
 * @author jitwxs
 * @date 2018/4/12 0:10
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrClient solrClient;

    public SearchItemResult searchItem(SolrQuery query) throws Exception  {
        QueryResponse response = solrClient.query(query);
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        SearchItemResult searchItemResult = new SearchItemResult();

        SolrDocumentList results = response.getResults();
        searchItemResult.setRecordCount(results.getNumFound());

        List<SearchItem> list = new ArrayList<>();
        for(SolrDocument document : results) {
            String id = (String) document.get("id");
            String title = (String) document.get("item_title");
            String sellPoint = (String) document.get("item_sell_point");
            Long price = (Long) document.get("item_price");
            String image = (String) document.get("item_image");
            String categoryName = (String) document.get("item_category_name");

            SearchItem item = new SearchItem();
            item.setId(id);
            item.setCategoryName(categoryName);
            item.setImage(image);
            item.setPrice(price);
            item.setSellPoint(sellPoint);

            // 高亮显示Title
            List<String> titles = highlighting.get(id).get("item_title");
            if(titles != null && titles.size() > 0) {
                item.setTitle(titles.get(0));
            } else {
                item.setTitle(title);
            }

            list.add(item);
        }

        searchItemResult.setItemList(list);

        return searchItemResult;
    }

}
