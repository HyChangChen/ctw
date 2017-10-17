import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.23:52
 * @Vistion：1.0
 * @Remark： 扩展Ongl, 用于判断mybatis mapper文件中的变量
 */


public class Ognl {
    /**
     * 可以用于判断 Map,Collection,String,Array是否为空
     *
     * @param o
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtils.isEmpty((String) o);
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o.getClass().isArray()) {
            return ArrayUtils.isEmpty((Object[]) o);
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        } else if (o instanceof Date) {
            return o == null;
        } else if (o instanceof Number) {
            return o == null;
        } else if (o instanceof Boolean) {
            return o == null;
        } else {
            throw new IllegalArgumentException(
                    "Illegal argument type,must be : Map,Collection,Array,String,Date,Number,Boolean. but was:" + o.getClass());
        }
    }

    /**
     * 可以用于判断 Map,Collection,String,Array是否不为空
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 判断是否满足o1和o2最少有一个不为空
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean hasValueEither(Object o1, Object o2) {
        return isNotEmpty(o1) || isNotEmpty(o2);
    }

    /**
     * 判断对象是否不为null并且其包含的字符不是全部由空格/换行符组成
     *
     * @param o
     * @return
     * @see StringUtils#isNotBlank(CharSequence)
     */
    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }

    /**
     * 判断对象是否为一个数值对象
     *
     * @param o
     * @return
     * @see NumberUtils#isNumber(String)
     */
    public static boolean isNumber(Object o) {
        if (o instanceof Number) {
            return true;
        } else if (o instanceof String) {
            return NumberUtils.isNumber((String) o);
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否为null或只由空格/换行符组成
     *
     * @param o
     * @return
     * @see StringUtils#isBlank(CharSequence)
     */
    public static boolean isBlank(Object o) {
        return StringUtils.isBlank((String) o);
    }

    /**
     * 根据传入的值比较查询是使用In 还是 not in
     * 1:in  0 : not in
     * @param o
     * @return
     */
    public static boolean  isEquals(Object o){
        if(Integer.parseInt((String) o)==1){
            return true;
        }else{
            return  false;
        }
    }

}
