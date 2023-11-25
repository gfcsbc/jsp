package  util;

public class StringUtil {
	public static boolean notEmpty(String str, String... p2) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		for (int i = 0; i < p2.length; i++) {
			if (p2[i] == null || "".equals(p2[i].trim())) {
				return false;
			}
		}
		return true;
	}
	public static boolean notEmpty(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	/**
	 * 将字符串根据分隔符拆分为数组，并把每个元素转换为int
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static int[] StringToIntArray(String str, String split) {
		if (notEmpty(str) && notEmpty(split)) {
			String[] sa = str.split(split);
			int len = sa.length;
			int[] retVal = new int[len];
			for (int i = 0; i < len; i++) {
				retVal[i] = Integer.valueOf(sa[i]);
			}
			return retVal;
		}
		return new int[0];
	}

	/**
	 * 数组转换为字符串
	 * @param objs
	 * @param split
	 * @return
	 */
	public static String arrayToString(Object[] objs, String split) {
		if (objs != null && objs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0;i < objs.length; i++) {
				sb.append(objs[i].toString());
				if( i < objs.length -1){
					sb.append(split);
				}
			}
			return sb.toString();
		}
		return null;
	}

	public static String dateStringFormat(String date) {
		String[] ds = date.split("-");
		date = ds[0];
		date += "-";
		if (ds[1].startsWith("0")) {
			ds[1] = ds[1].substring(1, ds[1].length());
		}
		if (ds[2].startsWith("0")) {
			ds[2] = ds[2].substring(1, ds[2].length());
		}
		date += ds[1];
		date += "-";
		date += ds[2];

		return date;
	}
	/**
     * 格式化数字不足补�?
     * 
     * @param num
     * @param value
     * @return
     */
    public static String format0String(int num, long value) {
        String result = (new Long(value)).toString();
        while (num > result.length()) {
            result = "0" + result;
        }
        return result;
    }

	public static void main(String[] args) {
		StringUtil.dateStringFormat("2011-12-19");
	}
}
