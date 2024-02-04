package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import dao.provider.CommonDaoProvider;
import model.DtOnlineTimeModel;

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
