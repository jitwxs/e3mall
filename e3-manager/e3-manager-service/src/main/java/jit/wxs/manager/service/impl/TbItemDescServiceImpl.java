package jit.wxs.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.mapper.TbItemDescMapper;
import jit.wxs.manager.pojo.TbItemDesc;
import jit.wxs.manager.service.TbItemDescService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品描述表 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemDescServiceImpl extends ServiceImpl<TbItemDescMapper, TbItemDesc> implements TbItemDescService {

    @Autowired
    TbItemDescMapper itemDescMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.item.pre}")
    private String redisItemPre;

    @Value("${redis.item.expire}")
    private int redisItemExpire;

    @Override
    public TbItemDesc geyById(Long id) {
        String redisKey = redisItemPre + ":" + id + ":DESC";
        try {
            String json = jedisClient.get("redisKey");
            if(StringUtils.isNotBlank(json)) {
                return JsonUtils.jsonToPojo(json,TbItemDesc.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc;
        List<TbItemDesc> list = itemDescMapper.selectList(
                new EntityWrapper<TbItemDesc>().eq("item_id", id)
        );
        if(list.size() == 0) {
            return null;
        } else {
            itemDesc = list.get(0);
        }

        try {
            jedisClient.set(redisKey, JsonUtils.objectToJson(itemDesc));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }

    @Override
    public boolean updateByItemId(TbItemDesc itemDesc) {
        itemDescMapper.update(itemDesc,
                new EntityWrapper<TbItemDesc>().eq("item_id", itemDesc.getItemId()));

        return true;
    }
}
