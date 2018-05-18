package common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 导入导出实体类的注解
 * @author Administrator
 *
 */
@Target(value=ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Excel
{
	/**
	 * 对应excel列名
	 * @return
	 */
	String name();
	/**
	 * 字段位于excel第几列
	 * @return
	 */
	int order();
	/**
	 * 是否导出(默认导出)
	 * @return
	 */
	boolean isExported() default true;
}
