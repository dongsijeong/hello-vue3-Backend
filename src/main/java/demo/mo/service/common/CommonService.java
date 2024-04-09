package demo.mo.service.common;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 共通機能のサービス
 *
 * @author: jdu7
 * @since: 2021-05-31
 **/
public interface CommonService {

    /**
     * オンラインであるかチェックする。
     * @return true:オンライン false:オンライン
     */
    boolean checkOnline();

    /**
     * オンライン情報取得
     * @return 情報
     */
    String getOnlinInfo();
}
