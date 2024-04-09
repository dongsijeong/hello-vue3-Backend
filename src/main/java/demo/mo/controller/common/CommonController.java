package demo.mo.controller.common;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.mo.model.ApiResponseModel;
import demo.mo.service.common.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 共通機能のコントロール
 *
 **/

@RestController
@RequestMapping("/v1/common")
@Tag(name = "CommonController", description = "共通機能")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * システム日付を取得
     *
     * @return システム日付（YYYY/MM/DD）
     */
    @Operation(summary = "共通機能・システム日付を取得", description = "システム日付を取得", tags = { "CommonController" })
    @RequestMapping(value = "/getSysDate", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<ApiResponseModel<String>> getSysDate() {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();

        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }
        String strDate = String.valueOf(cal.get(Calendar.DATE));
        if (strDate.length() == 1) {
            strDate = "0" + strDate;
        }
        result.add(strYear + "/" + strMonth + "/" + strDate);
        return  ApiResponseModel.wrapSuccessResponseResult(result);
    }
}
