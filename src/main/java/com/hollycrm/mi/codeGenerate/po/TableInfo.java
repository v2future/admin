package com.hollycrm.mi.codeGenerate.po;

import java.util.*;

/**
 * 描述
 */
public class TableInfo {
    private String name;
    private String comment="";
    private String primaryKeyName;
    private List<TableField> fields;

    public TableInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public String getPrimaryKeyName() {
        if (fields == null || fields.size() == 0) {
            return "";
        }
        for ( TableField field : fields) {
            if ( field.isKeyIdentityFlag())
                return field.getPropertyName();
        }
        return null;
    }
}
