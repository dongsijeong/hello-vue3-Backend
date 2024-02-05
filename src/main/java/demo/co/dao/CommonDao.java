package demo.co.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import demo.co.dao.provider.CommonDaoProvider;
import demo.co.model.DtOnlineTimeModel;

/**
 * 共通機能のDAO
 **/
@Mapper
public interface CommonDao {

    /**
     * 開始と終了時間取得
     *
     * @return 開始と終了時間取得
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getOnLineTime")
    DtOnlineTimeModel getOnLineTime();
}
