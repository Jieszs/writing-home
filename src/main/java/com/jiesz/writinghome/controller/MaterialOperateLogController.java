package com.jiesz.writinghome.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.MaterialOperateLog;
import com.jiesz.writinghome.service.IMaterialOperateLogService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @ApiOperation("添加素材操作日志")
    @PostMapping("/materialOperateLogs")
    public Result<MaterialOperateLog> insert(
            @RequestBody @Validated MaterialOperateLog materialOperateLog
    ) {
        materialOperateLog.insert();
        return Result.success(materialOperateLog);
    }


}
