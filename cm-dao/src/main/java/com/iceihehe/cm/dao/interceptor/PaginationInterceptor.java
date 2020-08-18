package com.iceihehe.cm.dao.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.HashMap;

@Intercepts(@Signature(type= StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PaginationInterceptor implements Interceptor {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(routingStatementHandler);
        while(metaObject.hasGetter("h")) {
            Object h = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(h);
        }
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();
        if (mapId.endsWith("List")) {

            //获取进行数据库操作时管理参数的handler
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            HashMap<String, Object> paramMap = (HashMap<String, Object>) parameterHandler.getParameterObject();
            int limitNum = 0;
            int offSetNum = 0;
            if (paramMap.containsKey("limitNum")) {
                limitNum = (int) paramMap.get("limitNum");
            }
            if (paramMap.containsKey("offsetNum")) {
                offSetNum = (int) paramMap.get("offsetNum");
            }
            if (offSetNum < 0 || limitNum <= 0) {
                return invocation.proceed();
            }
            logger.info("----------sql自动分页啦----------");

            String originSql = (String) metaObject.getValue("delegate.boundSql.sql");
            String limitSql = originSql.trim() + " limit " + offSetNum + "," + limitNum;
            metaObject.setValue("delegate.boundSql.sql", limitSql);
            return invocation.proceed();

        }

        return invocation.proceed();
    }
}
