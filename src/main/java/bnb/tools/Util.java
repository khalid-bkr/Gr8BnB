package bnb.tools;

public class Util {
	public static String combine(String tableName, String schema) {
		return tableName + "(" + schema + ")";
	}

	public static String insertStatement(String schemaTable) {
		StringBuilder builder = new StringBuilder("INSERT INTO " + schemaTable + " VALUES(");
		String[] heads = schemaTable.split(",");
		for (int i = 0; i < heads.length; i++) {
			builder.append("?");
			if (i < heads.length - 1) {
				builder.append(",");
			}
		}
		builder.append(");");
		return builder.toString();
	}

	public static String selectStatement(String tableName, String schema, String index) {
		return "SELECT " + schema + " FROM " + tableName + " WHERE " + index + "=?;";
	}

	public static String deleteStatement(String tableName, String index) {
		return "DELETE FROM " + tableName + " WHERE " + index + "=?;";
	}

	public static String updateStatement(String tableName, String index, String toChange) {
		return "UPDATE " + tableName + " SET " + toChange + "=? WHERE " + index + "=?;";
	}

	public static String innerJoinOn(String table1, String table2, String index) {
		return table1 + " INNER JOIN " + table2 + " ON " + table1 + "." + index + "=" + table2 + "." + index;
	}
}
