package info.dreamcoder;

public class TableOptions {
    private boolean needId = true;
    private String idType = "bigint";
    private String primaryKey = "id";
    private String options = "";

    private String as = "";

    public boolean isNeedId() {
        return needId;
    }

    public String getIdType() {
        return idType;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getOptions() {
        return options;
    }

    public String getAs() {
        return as;
    }

    public void disableId() {
        this.needId = false;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setAs(String as) {
        this.as = as;
    }
}