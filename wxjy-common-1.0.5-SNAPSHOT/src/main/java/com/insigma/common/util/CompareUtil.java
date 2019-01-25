package com.insigma.common.util;

import java.util.Date;
/**
 * ֵ�ȽϹ�����
 * @author zhangy
 *
 */
public class CompareUtil {
	/**
	 * @$comment �Ƚ������ַ����Ƿ���ȣ�null�Ϳ��ַ�������ͬ��
	 * @param str1
	 * @param str2
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareString(String str1,String str2){
		boolean r=true;
		if((str1==null||str1.equals(""))&&str2!=null&&!str2.equals("")){
			r=false;
		}else if(str1!=null&&!str1.equals("")&&(str2==null||str2.equals(""))){
			r=false;
		}else if(str1!=null&&str2!=null){
			if(!str1.equals(str2)){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ�����Long���͵�ֵ�Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareLong(Long a,Long b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.longValue()!=b.longValue()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ�����Integer���͵�ֵ�Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareInteger(Integer a,Integer b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.intValue()!=b.intValue()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ�����Short���͵�ֵ�Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareShort(Short a,Short b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.intValue()!=b.intValue()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ�����Float���͵�ֵ�Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareFloat(Float a,Float b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.floatValue()!=b.floatValue()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ�����Double���͵�ֵ�Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareDouble(Double a,Double b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.doubleValue()!=b.doubleValue()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * @$comment �Ƚ����������Ƿ����
	 * @param a
	 * @param b
	 * @return ��ȷ���true�����򷵻�false
	 */
	public static boolean compareDate(Date a,Date b){
		boolean r=true;
		if(a==null&&b!=null){
			r=false;
		}else if(a!=null&&b==null){
			r=false;
		}else if(a!=null&&b!=null){
			if(a.getTime()!=b.getTime()){
				r=false;
			}
		}
		return r;
	}
	
	/**
	 * �ж��Ƿ��ǻ�������
	 * 
	 * @param cls
	 * @return true �������� false ���ǻ�������
	 */
	@SuppressWarnings("unchecked")
	public static boolean isBasicClass(Class cls) {
		if (cls == String.class) {
			return true;
		} else if (cls == Integer.class || cls == int.class) {
			return true;
		} else if (cls == Short.class || cls == short.class) {
			return true;
		} else if (cls == Float.class || cls == float.class) {
			return true;
		} else if (cls == Double.class || cls == double.class) {
			return true;
		} else if (cls == Long.class || cls == long.class) {
			return true;
		} else if (cls == Date.class) {
			return true;
		}

		return false;
	}

}
