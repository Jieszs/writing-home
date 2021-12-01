package com.jiesz.writinghome.controller;

import com.jiesz.writinghome.common.MaterialOperateType;
import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.entity.Material;
import com.jiesz.writinghome.entity.MaterialOperateLog;
import com.jiesz.writinghome.entity.MaterialType;
import com.jiesz.writinghome.service.IMaterialService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 素材操作日志 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "素材操作日志管理")
public class MaterialOperateLogController {
    @Resource
    private IMaterialService iMaterialService;

    @ApiOperation("添加素材操作日志")
    @PostMapping("/materialOperateLogs")
    public Result<MaterialOperateLog> insert(
            @RequestParam @ApiParam(value = "素材Id", required = true) Integer materialId,
            @RequestParam @ApiParam(value = "操作类型（1-点赞 2-取消点赞 3-收藏）", required = true) Integer operateType,
            @RequestParam(required = false) @ApiParam(value = "分类id") List<Integer> typeIds
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));

        MaterialOperateLog materialOperateLog = MaterialOperateLog.builder()
                .userId(userId)
                .materialId(materialId)
                .operateType(operateType)
                .build();
        materialOperateLog.insert();
        if (operateType.equals(MaterialOperateType.COLLECT)) {
            Material one = iMaterialService.getById(materialId);
            one.setUserId(userId).setTypeIds(typeIds);
            one.insert();
        }
        return Result.success(materialOperateLog);
    }


}
