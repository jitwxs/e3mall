package jit.wxs.search.activemq;

import jit.wxs.common.pojo.SearchItem;
import jit.wxs.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author jitwxs
 * @date 2018/4/15 17:13
 */
public class AddItemMessageListener implements MessageListener {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Override
    public void onMessage(Message message) {
        try{
            // 接收manager-service发送过来的新增的商品Id
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();
            Long id = new Long(text);
            // 等待商品先提交，避免查询为空
            Thread.sleep(1000);
            SearchItem searchItem = searchItemMapper.getById(id);
            // 将索引商品对象保存到索引库
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", searchItem.getId());
            document.addField("item_category_name",searchItem.getCategoryName());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_sell_point",searchItem.getSellPoint());
            document.addField("item_title",searchItem.getTitle());
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
