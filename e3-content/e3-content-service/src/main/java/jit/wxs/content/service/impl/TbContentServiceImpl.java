package jit.wxs.content.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.content.service.TbContentService;
import jit.wxs.manager.mapper.TbContentMapper;
import jit.wxs.manager.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbContentServiceImpl extends ServiceImpl<TbContentMapper, TbContent> implements TbContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.CONTENT_KEY}")
    private String CONTENT_KEY;

    /**
     * 删除CONTENT_KEY中指定field
     */
    private void deleteContentKeyFromRedis(Long cid) {
        try {
            jedisClient.hdel(CONTENT_KEY, cid + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TbContent> listByCategoryId(Long cid) {
        try {
            // 如果缓存存在的话，直接从缓存中取
            String json = jedisClient.hget(CONTENT_KEY, cid + "");
            if(StringUtils.isNotBlank(json)) {
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TbContent> contents = contentMapper.selectList(new EntityWrapper<TbContent>() .eq("category_id", cid));

        try {
            // 加入缓存
            jedisClient.hset(CONTENT_KEY, cid+"", JsonUtils.objectToJson(contents));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contents;
    }

    @Override
    public void addContent(TbContent tbContent) {
        // 更新缓存
        deleteContentKeyFromRedis(tbContent.getCategoryId());

        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        contentMapper.insert(tbContent);
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            return;
        }

        // 更新缓存
        TbContent tbContent = contentMapper.selectById(id);
        deleteContentKeyFromRedis(tbContent.getCategoryId());

        contentMapper.deleteById(id);
    }
}