package demo.mo.service.impl.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.MESSAGECODE;
import demo.mo.dao.CommonDao;
import demo.mo.exception.SystemException;
import demo.mo.model.DtOnlineTimeModel;
import demo.mo.service.common.CommonService;
import demo.mo.util.StringCheck;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import lombok.extern.log4j.Log4j2;
/**
 * 
 */
@Service
@Log4j2
@Transactional(readOnly = false, isolation = Isolation.DEFAULT,
  rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * オンラインであるかチェックする。
     * @return true:オンライン false:オンライン
     */
   @Override
   public  boolean checkOnline() {
       DtOnlineTimeModel onLineInfo = commonDao.getOnLineTime();
       if (onLineInfo == null) {
           throw new SystemException("DT_ONLINE_TIMEテーブルに情報が登録されない。");
       }
       if (!Strings.hasText(onLineInfo.getKaisiTm()) || !Strings.hasText(onLineInfo.getSyuryoTm())
               || !StringCheck.checkNumeric(onLineInfo.getKaisiTm())
               || !StringCheck.checkNumeric(onLineInfo.getSyuryoTm())) {
           throw new SystemException("DT_ONLINE_TIMEテーブルに登録情報がただしくないです。");
       }
       Date dateCurrent = new Date();
       DateFormat dateFormat = new SimpleDateFormat("HHmm", Locale.JAPAN);  // 24時間での時刻フォーマット
       String strTime = dateFormat.format(dateCurrent);
       if ((onLineInfo.getKaisiTm().compareTo(strTime) <= 0)
               && (onLineInfo.getSyuryoTm().compareTo(strTime) > 0)) {
           return true;
       }
       return false;
   }

   /**
    * 情報取得
    * @return 情報
    */
   @Override
public  String getOnlinInfo() {
       DtOnlineTimeModel onLineInfo = commonDao.getOnLineTime();
       if (onLineInfo == null) {
           throw new SystemException("DT_ONLINE_TIMEテーブルに情報が登録されない。");
       }
       if (!Strings.hasText(onLineInfo.getKaisiTm()) || !Strings.hasText(onLineInfo.getSyuryoTm())
               || !StringCheck.checkNumeric(onLineInfo.getKaisiTm())
               || !StringCheck.checkNumeric(onLineInfo.getSyuryoTm())) {
           throw new SystemException("DT_ONLINE_TIMEテーブルに登録情報がただしくないです。");
       }
       String onlineStart = onLineInfo.getKaisiTm();
       String onlineEnd = onLineInfo.getSyuryoTm();
       onlineStart = "0000" + onlineStart;
       onlineEnd = "0000" + onlineEnd;
       onlineStart = onlineStart.substring(onlineStart.length() - 4);
       onlineEnd = onlineEnd.substring(onlineEnd.length() - 4);
       onlineStart = onlineStart.substring(0, 2) + ":" + onlineStart.substring(2);
       onlineEnd = onlineEnd.substring(0, 2) + ":" + onlineEnd.substring(2);
       String onlineTime =  onlineStart + "～" + onlineEnd;
       return DataAccessConfig.getMessage(MESSAGECODE.E_CM22007_22007, onlineTime);
   }
}
