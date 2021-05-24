package com.jd.socialmanager.controller;

import com.jd.socialmanager.entity.BlockDO;
import com.jd.socialmanager.service.IBlockService;
import com.jd.socialmanager.util.AjaxResult;
import com.jd.socialmanager.util.Data;
import com.jd.socialmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private IBlockService blockService;

    /**
     * 跳转公寓页面
     */
    @GetMapping("/blockList")
    public String blockList() {
        return "/block/blockList";
    }

    /**
     * 异步加载公寓列表
     */
    @PostMapping("/getBlockList")
    @ResponseBody
    public Object getBlockList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows, String blockName, String from) {
        BlockDO selectBlock = new BlockDO();
        selectBlock.setBlockName(blockName);
        PageBean<BlockDO> pageBean = new PageBean<>(page, rows);
        List<BlockDO> blocks = blockService.queryAll(selectBlock, pageBean.getStartIndex(), rows);
        if (!"".equals(blockName)) {
            pageBean.setDatas(blocks);
        }
        pageBean.setTotalsize(blocks.size());
        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 添加公寓信息
     */
    @RequestMapping("/addBlock")
    @ResponseBody
    public AjaxResult addBlock(BlockDO block) {
        AjaxResult ajaxResult = new AjaxResult();
        BlockDO blockResult = blockService.insert(block);
        if (blockResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }

    /**
     * 修改公寓信息
     */
    @PostMapping("/editBlock")
    @ResponseBody
    public AjaxResult editBlock(BlockDO block) {
        AjaxResult ajaxResult = new AjaxResult();
        BlockDO blockResult = blockService.update(block);
        if (blockResult != null) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }

    /**
     * 删除公寓信息
     */
    @PostMapping("/deleteBlock")
    @ResponseBody
    public AjaxResult deleteBlock(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        List<Integer> ids = data.getIds();
        List<Integer> fails = new ArrayList<>();
        for (int id : ids) {
            boolean isDelete = blockService.deleteById(id);
            if (!isDelete) {
                fails.add(id);
            }
        }
        if (fails.size() == 0) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("删除成功");

        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }
}
