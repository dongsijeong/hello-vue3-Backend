/*
 * 権限情報管理クラス
 */
package demo.co.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import demo.co.dao.CommonDao;
import demo.co.util.ActionInfoManger;

@Component
public class AuthorityConfig implements InitializingBean {
    private static AuthorityConfig sInstance;

//    private Map<String, List<String>> gamenIdMap = new HashMap<>();
//
//    private Map<String, List<String>> tabidMap = new HashMap<>();

    @Autowired
    private CommonDao commonDao;

    /**
     * 画面IDリストとコントロールIDリストの取得
     */
    public void afterPropertiesSet() throws Exception {
        sInstance = this;
        ActionInfoManger.loadInfo();
    }
}
