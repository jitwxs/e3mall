package jit.wxs.search.service.impl;

import jit.wxs.common.pojo.SearchItem;
import jit.wxs.common.utils.E3Result;
import jit.wxs.search.mapper.SearchItemMapper;
import jit.wxs.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/11 22:17
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public E3Result addToSolr() {
        try {
            List<SearchItem> list = searchItemMapper.listSearchItem();
            for(SearchItem searchItem : list) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_category_name",searchItem.getCategoryName());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_sell_point",searchItem.getSellPoint());
                document.addField("item_title",searchItem.getTitle());
                solrClient.add(document);
                solrClient.commit();
            }
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.error(null);
        }
    }
}
