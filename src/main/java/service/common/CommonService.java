package service.common;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.lawson.mo.model.CommonBunruiListModel;
import jp.co.lawson.mo.model.CommonDropdownListModel;
import jp.co.lawson.mo.model.LawsonBaseModel;
import jp.co.lawson.mo.model.Lmv04011DataModel;
import jp.co.lawson.mo.model.MaDealerModel;
import jp.co.lawson.mo.model.MaGenzairyoModel;
import jp.co.lawson.mo.model.MaHozonOndotaiModel;
import jp.co.lawson.mo.model.MaSClassModel;
import jp.co.lawson.mo.model.MaSDeptModel;
import jp.co.lawson.mo.model.MaSSubclassModel;
import jp.co.lawson.mo.param.CommomInputCheckParam;
import jp.co.lawson.mo.param.DownloadCsvParam;
import jp.co.lawson.mo.param.DownloadImageParam;
import jp.co.lawson.mo.param.Lcu04021ComListParam;

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
