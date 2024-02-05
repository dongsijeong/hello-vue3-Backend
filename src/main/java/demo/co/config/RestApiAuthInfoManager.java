package demo.co.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import demo.co.model.ApiAuthInfo;
import demo.co.model.ApiDetailInfo;
import springfox.documentation.service.ApiInfo;

/**
 * 入力チェック設定情報管理マネージャー
 * @author zchang4
 *
 */
@Component
public class RestApiAuthInfoManager implements InitializingBean {

    private  Map<String, ApiDetailInfo> apiDetailMap = new HashMap<>();

    @Autowired
    private ResourceLoader resourceLoader;

    private static RestApiAuthInfoManager sInstance;

    /**
     * 入力ファイルのロード
     */
    @SuppressWarnings("all")
    public void afterPropertiesSet() throws Exception {
        sInstance = this;
        String pattern = "classpath*:apiauth/restapiauth.xml";
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
        for (Resource resource : resources) {
            SAXReader saxReader = new SAXReader();
            Document document = null;
            document = saxReader.read(resource.getInputStream());
            Element root = document.getRootElement();
            for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                Element featura = (Element) iterator.next();
                if (!featura.getName().equals("auth")) {
                    continue;
                }
                JAXBContext jaxbC = JAXBContext.newInstance(ApiAuthInfo.class);
                Unmarshaller us = jaxbC.createUnmarshaller();
                InputStream bais = new ByteArrayInputStream(featura.asXML().getBytes("utf-8"));
                ApiAuthInfo info = (ApiAuthInfo) us.unmarshal(bais);
                List<ApiInfo> apiList = info.getApiList();
                for (ApiInfo apiinfo : apiList) {
                    ApiDetailInfo detail = null;
//                    if (apiDetailMap.containsKey(apiinfo.getPath())) {
//                        detail = apiDetailMap.get(apiinfo.getPath());
//                    } else {
//                        detail = new ApiDetailInfo();
//                        apiDetailMap.put(apiinfo.getPath(), detail);
//                    }
                    setDetail(apiinfo, detail);
                }
            }
        }
    }

    private void setDetail(ApiInfo apiInfo, ApiDetailInfo detail) {
        
    }

    /**
     * API詳細情報を取得する
     * @param path
     * @return API詳細情報
     */
    public static ApiDetailInfo getApiDetailInfo(String path) {
        if (sInstance.apiDetailMap.containsKey(path)) {
            return sInstance.apiDetailMap.get(path);
        }
        return null;
    }

}
