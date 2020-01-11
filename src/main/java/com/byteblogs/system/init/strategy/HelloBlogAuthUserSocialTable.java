package com.byteblogs.system.init.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 青涩知夏
 */
@Service
@Slf4j
public class HelloBlogAuthUserSocialTable implements TableInfoService {

    @Override
    public void builderTable(final Statement stat) {
        try {
            stat.execute(createHelloBlogAuthUserSocial());
            log.info("初始化hello_blog_auth_user_social完成");
        } catch (final SQLException e) {
            log.info("初始化hello_blog_auth_user_social失败", e);
        }
    }

    @PostConstruct
    public void beforePropertiesSet(){
        TableInitFactory.register("hello_blog_auth_user_social", this);
    }

    private static String createHelloBlogAuthUserSocial() {
        return "CREATE TABLE `hello_blog_auth_user_social` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `code` varchar(32) NOT NULL COMMENT 'qq、csdn、wechat、weibo、email等',\n" +
                "  `account` varchar(255) DEFAULT NULL COMMENT '社交账户',\n" +
                "  `icon` varchar(255) DEFAULT NULL COMMENT '图标',\n" +
                "  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',\n" +
                "  `show_type` smallint(6) NOT NULL COMMENT '展示类型( 1、显示二维码，2、显示账号，3、跳转链接)',\n" +
                "  `url` varchar(255) DEFAULT NULL COMMENT '跳转链接',\n" +
                "  `remark` varchar(255) DEFAULT NULL COMMENT '备注',\n" +
                "  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "  `is_deleted` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户表社交信息表';";
    }
}