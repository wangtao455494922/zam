package com.wjt.zam.common.rewrite;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**  

* <p>Description:获取.properties属性文件信息 </p>  
* @author wjt  
* @date 2018年6月29日  

*/ 
public class ZamPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String,String> ctxPropMap;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropMap = new HashMap<>();
        for (Object key : props.keySet()){
            String keyStr = key.toString();
            String value = String.valueOf(props.get(keyStr));
            ctxPropMap.put(keyStr,value);
        }
	}
	
	public static String getCtxProp(String name) {
        return ctxPropMap.get(name);
    }

    public static Map<String, String> getCtxPropMap() {
        return ctxPropMap;
    }
}
