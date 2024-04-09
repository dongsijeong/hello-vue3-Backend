package demo.mo.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import demo.mo.exception.BusinessException;
import demo.mo.exception.CheckInputException;


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
    @AfterThrowing(pointcut = "execution(* demo.mo..*.*(..))", throwing = "error")
    public void afterExceptionAdvice(JoinPoint jp, Exception error) {
        if (error instanceof CheckInputException || error instanceof BusinessException) {
            return;
        }
    }



}
