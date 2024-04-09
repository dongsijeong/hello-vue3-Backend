package demo.mo.util;

/**
 * 挿入/更新時に有効開始日/終了日のチェックをする機能の基底Bean。<br>
 *
 */
public interface InfYukoBase {

    /**
     * 有効開始日を取得する。
     *
     * @return 有効開始日
     */
    String getYukokaisiDT();

    /**
     * 有効終了日を取得する。
     *
     * @return 有効終了日
     */
    String getYukosyuryoDT();
}
