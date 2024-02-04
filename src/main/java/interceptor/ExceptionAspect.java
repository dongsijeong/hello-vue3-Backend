package interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import exception.BusinessException;
import exception.CheckInputException;

/**
 * エクセプションインターセプター
 * @author zchang4
 *
 */
//@Aspect
//@Component
public class ExceptionAspect {

    /**
     *エラーの検知対象となるエラー情報のみを出力する
     * @param jp
     * @param error
     */
    @AfterThrowing(pointcut = "execution(* jp.co.lawson.mo..*.*(..))", throwing = "error")
    public void afterExceptionAdvice(JoinPoint jp, Exception error) {
        if (error instanceof CheckInputException || error instanceof BusinessException) {
            return;
        }
//        String typeName = jp.getSignature().getDeclaringTypeName();
//        String meshot = jp.getSignature().getName();
//        String errMsg = typeName + "\t" + meshot + "\t" + error.getMessage();
//        LogUtil.outErrorlog(errMsg);
    }



}
