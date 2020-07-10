package com.hollycrm.mi.codeGenerate.config.converts;

import com.hollycrm.mi.codeGenerate.config.DbColumnType;
import com.hollycrm.mi.codeGenerate.config.ITypeConvert;

/**
 * 描述
 */
public class OracleTypeConvert implements ITypeConvert {
    public OracleTypeConvert() {
    }

    public DbColumnType processTypeConvert(String fieldType) {
        String t = fieldType.toUpperCase();
        if (t.contains("CHAR")) {
            return DbColumnType.STRING;
        } else if (!t.contains("DATE") && !t.contains("TIMESTAMP")) {
            if (t.contains("NUMBER")) {
                if (t.matches("NUMBER\\(+\\d\\)")) {
                    return DbColumnType.INTEGER;
                } else {
                    return t.matches("NUMBER\\(+\\d{2}+\\)") ? DbColumnType.LONG : DbColumnType.DOUBLE;
                }
            } else if (t.contains("FLOAT")) {
                return DbColumnType.FLOAT;
            } else if (t.contains("clob")) {
                return DbColumnType.CLOB;
            } else if (t.contains("BLOB")) {
                return DbColumnType.OBJECT;
            } else if (t.contains("binary")) {
                return DbColumnType.BYTE_ARRAY;
            } else {
                return t.contains("RAW") ? DbColumnType.BYTE_ARRAY : DbColumnType.STRING;
            }
        } else {
            return DbColumnType.DATE;
        }
    }
}
