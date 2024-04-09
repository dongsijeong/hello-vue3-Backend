package demo.mo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import demo.mo.dao.provider.CommonDaoProvider;
import demo.mo.model.DtOnlineTimeModel;
import demo.mo.model.DtPlayerKinoHyojiModel;
import demo.mo.model.DtPlayerTabHyojiModel;

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

    /**
     * 画面IDリスト取得
     * @param playerCd
     * @return 画面IDリスト
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerKinoHyojiListByCd")
    List<DtPlayerKinoHyojiModel> getDtPlayerKinoHyojiListByCd(String playerCd);

    /**
     * コントロールIDリスト取得
     * @param playerCd
     * @return コントロールIDリスト
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerTabHyojiListByCd")
    List<DtPlayerTabHyojiModel> getDtPlayerTabHyojiListByCd(String playerCd);

    /**
     * プレイヤーコード取得
     * @param userId ユーザーID
     * @return プレイヤーコード
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerSecuritynByuserId")
    String getDtPlayerSecuritynByuserId(String userId);

    /**
     * プレイヤーコード取得
     * @param userId ユーザーID
     * @param sosikiCD 組織コード
     * @param syokuiCD 職位コード
     * @return プレイヤーコード
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerSecurityn")
    String getDtPlayerSecurityn(String userId, String sosikiCD, String syokuiCD);

    /**
     *  プレイヤーコード取得
     * @param torihikisakiCd 取引コード
     * @param riyosyaCd 利用者コード
     * @return  プレイヤーコード取得
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerSecurityG")
    String getDtPlayerSecurityG(String torihikisakiCd, String riyosyaCd);

    /**
     *  プレイヤーコード取得
     * @param torihikisakiCd 取引コード
     * @param riyosyaCd 利用者コード
     * @return  プレイヤーコード取得
     */
    @SelectProvider(type = CommonDaoProvider.class, method = "getDtPlayerSecurityG2")
    String getDtPlayerSecurityG2(String torihikisakiCd, String riyosyaCd);
}
