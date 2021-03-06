/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.kaikeba.co
 */
package com.jshop.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import com.jshop.logging.aop.log.Log;
import com.jshop.modules.activity.domain.StoreCouponIssue;
import com.jshop.modules.activity.service.StoreCouponIssueService;
import com.jshop.modules.activity.service.dto.StoreCouponIssueQueryCriteria;
import com.jshop.utils.OrderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author jack胡
*/
@Api(tags = "商城:优惠券发布管理")
@RestController
@RequestMapping("api")
public class StoreCouponIssueController {

    private final StoreCouponIssueService storeCouponIssueService;

    public StoreCouponIssueController(StoreCouponIssueService storeCouponIssueService) {
        this.storeCouponIssueService = storeCouponIssueService;
    }

    @Log("查询已发布")
    @ApiOperation(value = "查询已发布")
    @GetMapping(value = "/StoreCouponIssue")
    @PreAuthorize("@el.check('admin','STORECOUPONISSUE_ALL','STORECOUPONISSUE_SELECT')")
    public ResponseEntity getStoreCouponIssues(StoreCouponIssueQueryCriteria criteria, Pageable pageable){
        criteria.setIsDel(0);
        return new ResponseEntity(storeCouponIssueService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("发布")
    @ApiOperation(value = "发布")
    @PostMapping(value = "/StoreCouponIssue")
    @PreAuthorize("@el.check('admin','STORECOUPONISSUE_ALL','STORECOUPONISSUE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody StoreCouponIssue resources){
        if(ObjectUtil.isNotNull(resources.getStartTimeDate())){
            resources.setStartTime(OrderUtil.
                    dateToTimestamp(resources.getStartTimeDate()));
        }
        if(ObjectUtil.isNotNull(resources.getEndTimeDate())){
            resources.setEndTime(OrderUtil.
                    dateToTimestamp(resources.getEndTimeDate()));
        }
        if(resources.getTotalCount() > 0) {
            resources.setRemainCount(resources.getTotalCount());
        }
        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        return new ResponseEntity(storeCouponIssueService.save(resources),HttpStatus.CREATED);
    }

    @Log("修改状态")
    @ApiOperation(value = "修改状态")
    @PutMapping(value = "/StoreCouponIssue")
    @PreAuthorize("@el.check('admin','STORECOUPONISSUE_ALL','STORECOUPONISSUE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody StoreCouponIssue resources){
        storeCouponIssueService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/StoreCouponIssue/{id}")
    @PreAuthorize("@el.check('admin','STORECOUPONISSUE_ALL','STORECOUPONISSUE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        StoreCouponIssue resources = new StoreCouponIssue();
        resources.setId(id);
        resources.setIsDel(1);
        storeCouponIssueService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.OK);
    }
}
