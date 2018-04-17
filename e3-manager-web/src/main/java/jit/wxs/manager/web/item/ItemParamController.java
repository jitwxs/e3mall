package jit.wxs.manager.web.item;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jit.wxs.common.pojo.EasyUIDataGrid;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbItemParam;
import jit.wxs.manager.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 商品规格Controller
 * @author jitwxs
 * @date 2018/4/6 21:53
 */
@RestController
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private TbItemParamService tbItemParamService;

    @GetMapping("/list")
    public EasyUIDataGrid getItemParamList(Integer page, Integer rows) {
        Page<TbItemParam> itemPage = tbItemParamService.selectPage(new Page<>(page, rows));
        List<TbItemParam> list = itemPage.getRecords();

        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setTotal(itemPage.getTotal());
        result.setRows(list);

        return result;
    }

    @GetMapping("/query/itemcatid/{id}")
    public E3Result getItemCatParam(@PathVariable Long id) {
        List<TbItemParam> params = tbItemParamService.selectByItemCatId(id);

        if (params == null || params.size() == 0) {
            return E3Result.error(null);
        } else {
            return E3Result.ok(params.get(0));
        }
    }

    @PostMapping("/save/{cid}")
    public E3Result saveItemParam(@PathVariable Long cid, String paramData) {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        tbItemParamService.insert(itemParam);
        return E3Result.ok();
    }

    @PostMapping("/delete")
    public E3Result deleteItemParam(Long[] ids) {
        for(Long id : ids) {
            tbItemParamService.deleteById(id);
        }
        return E3Result.ok();
    }
}
