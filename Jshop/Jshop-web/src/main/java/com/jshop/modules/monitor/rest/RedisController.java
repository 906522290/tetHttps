package com.jshop.modules.monitor.rest;

import com.jshop.logging.aop.log.Log;
import com.jshop.modules.monitor.domain.vo.RedisVo;
import com.jshop.modules.monitor.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack胡
 */
@Api(tags = "redis缓存管理")
@RestController
@RequestMapping("api")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Log("查询Redis缓存")
    @GetMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_SELECT')")
    public ResponseEntity getRedis(String key, Pageable pageable){
        return new ResponseEntity(redisService.findByKey(key,pageable), HttpStatus.OK);
    }

    @Log("删除Redis缓存")
    @DeleteMapping(value = "/redis")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity delete(@RequestBody RedisVo resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        redisService.delete(resources.getKey());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("清空Redis缓存")
    @DeleteMapping(value = "/redis/all")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL','REDIS_DELETE')")
    public ResponseEntity deleteAll(){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        redisService.flushdb();
        return new ResponseEntity(HttpStatus.OK);
    }
}
