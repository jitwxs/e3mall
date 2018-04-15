package jit.wxs.manager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.IDUtils;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.mapper.TbItemDescMapper;
import jit.wxs.manager.mapper.TbItemMapper;
import jit.wxs.manager.mapper.TbItemParamItemMapper;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.pojo.TbItemDesc;
import jit.wxs.manager.pojo.TbItemParamItem;
import jit.wxs.manager.service.TbItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Date;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemServiceImpl extends ServiceImpl<TbItemMapper, TbItem> implements TbItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private Destination addItemDestination;

    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.item.pre}")
    private String redisItemPre;

    @Value("${redis.item.expire}")
    private int redisItemExpire;

    @Override
    public boolean insert(TbItem item, String desc, String itemParams) {
        Date date = new Date();
        long id = IDUtils.genItemId();
        // 商品状态，1-正常，2-下架，3-删除
        item.setId(id);
        item.setStatus(1);
        item.setCreated(date);
        item.setUpdated(date);
        int i = itemMapper.insert(item);

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        int j = itemDescMapper.insert(itemDesc);

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(id);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        int k = itemParamItemMapper.insert(itemParamItem);

        if(!(i == 1 && j == 1 && k == 1)) {
            return false;
        }

        // 将商品Id放入ActiveMQ的Topic
        jmsTemplate.send(addItemDestination, session -> session.createTextMessage(id+""));

        return true;
    }

    @Override
    public TbItem getById(Long id) {
        String redisKey = redisItemPre + ":" + id + ":BASE";
        try{
            String json = jedisClient.get(redisKey);
            if(StringUtils.isNotBlank(json)) {
                return JsonUtils.jsonToPojo(json, TbItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem item = itemMapper.selectById(id);

        try{
            jedisClient.set(redisKey, JsonUtils.objectToJson(item));
            jedisClient.expire(redisKey,redisItemExpire);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
