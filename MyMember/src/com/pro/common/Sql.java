package com.pro.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/*
 * ע����sql���ģ����ֻ�ṩ�˴��ڻ���sql��䣬Ŀǰ֧�ֵ���������и���sql��䣬�����Լ���д
 * author Andrew
 * date   2012-12-11
 */
public class Sql {

	// ��ҳ���ģ��(oracle��ҳ������ķ�ҳ��֪��)
	public static String getPageSql(String tableName, int start, int rowNum) {
		int end = start + rowNum;
		String sql = "select rownum rm,* from " + tableName;
		String sqlPage = "select * from (" + sql + ") where rm>" + start
				+ " and rm<" + end;
		return sqlPage;
	}

	// ���id���������(һ�������޸Ĺ����и��id���һ�����)
	public static String getObjectSql(String tableName, String id, Object value) {
		String sql = "";
		if (value instanceof Integer) {
			sql = "select * from " + tableName + " where " + id + "=" + value;
		} else if (value instanceof String) {
			sql = "select * from " + tableName + " where " + id + "='" + value
					+ "'";
		}
		return sql;
	}

	// �����sql,�˷�������û�п��ǵ���ʱ��Ϊ���(�����ѯ)
	public static String getMulArgsSql(String tableName,
			final Map<String, Object> condition) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(tableName).append(" where 1=1");
		for (String key : condition.keySet()) {
			Object value = condition.get(key);
			if (value instanceof Integer || value instanceof Double) {
				sql.append(" and " + key).append("=").append(value);
			} else if (value instanceof String) {
				sql.append(" and " + key).append("='").append(value)
						.append("'");
			}
		}
		return sql.toString();
	}

	// ɾ��sql(���id����ɾ��,����֧�ָ�ݶ��idһ��ɾ��)
	public static String getDelSql(String tableName, String id,
			final Object[] value) {
		String ids = "";
		for (int i = 0; i < value.length; i++) {
			if (value instanceof Integer[]) {
				if (ids.equals("")) {
					ids = ids + value[i];
				} else {
					ids = ids + "," + value[i];
				}
			} else if (value instanceof String[]) {
				if (ids.equals("")) {
					ids = ids + "'" + value[i] + "'";
				} else {
					ids = ids + "," + "'" + value[i] + "'";
				}
			}
		}
		String sql = "delete from " + tableName + " where " + id + " in(" + ids
				+ ")";
		return sql;
	}

	// �޸Ĺ���sql���
	public static String getModiSql(String tableName, String id,
			Object idValue, final Map<String, Object> condition) {
		StringBuilder sql = new StringBuilder();
		sql.append("update " + tableName + " set ");
		int tag = 0;
		for (String key : condition.keySet()) {
			Object value = condition.get(key);
			if (value instanceof Integer || value instanceof Double) {
				if (tag == 0) {
					sql.append(key).append("=").append(value);
					tag++;
				} else {
					sql.append(",").append(key).append("=").append(value);
				}
			} else if (value instanceof String) {
				if (tag == 0) {
					sql.append(key).append("='").append(value).append("'");
					tag++;
				} else {
					sql.append(",").append(key).append("='").append(value)
							.append("'");
				}
			}
		}

		if (idValue instanceof String) {
			sql.append(" where ").append(id).append("='").append(idValue)
					.append("'");
		} else if (idValue instanceof Integer) {
			sql.append(" where ").append(id).append("=").append(idValue);
		}
		return sql.toString();
	}

	// ���빦��sql���
	// Ҫ������ֶκͶ�Ӧ��ֵһ��ֵ����ʽ����Map������
	public static String getInsertSql(String tableName,
			final Map<String, Object> condition) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(tableName).append("(");
		Set<String> ketset = condition.keySet();
		int tag = 1;
		for (String key : ketset) {
			if (tag == ketset.size()) {
				sql.append(key);
			} else {
				sql.append(key).append(",");
			}
			tag++;
		}
		tag = 1;
		sql.append(") values(");
		Set<Entry<String, Object>> entrySet = condition.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			Object value = entry.getValue();
			if (value instanceof Integer || value instanceof Double) {
				if (tag == entrySet.size()) {
					sql.append(value);
				} else {
					sql.append(value).append(",");
				}

			} else if (value instanceof String) {
				if (tag == entrySet.size()) {
					sql.append("'").append(value).append("'");
				} else {
					sql.append("'").append(value).append("',");
				}
			}
			tag++;
		}
		sql.append(")");
		return sql.toString();
	}

	public static void main(String[] args) {
		try {
			RandomAccessFile raf01 = new RandomAccessFile("D:/mms/one.txt",
					"rw");
			// 写入一组数据限定字符串4位
			String strTest = "abcd";
			int iTest = 33;
			System.out.println();
			raf01.write(strTest.getBytes());
			raf01.writeInt(iTest);
			// 再写入一组数据
			strTest = "mnuv";
			iTest = 32;
			raf01.write(strTest.getBytes());
			raf01.writeInt(iTest);
			raf01.close();

			// 下面我们读取第二组数据
			RandomAccessFile raf02 = new RandomAccessFile("D:/mms/one.txt", "r");
			raf02.skipBytes(8);
			byte[] bTest = new byte[4];
			raf02.read(bTest);
			System.out.println("字符串数据：" + new String(bTest) + ",整型数据为："
					+ raf02.readInt());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
