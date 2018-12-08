public class Data {
    String key;
    String value;
    int hash;

    Data(String k, String val) {
        key = k;
        value = val;
        hash = this.hashCode();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < key.length(); i++) {
            hash = hash*31 + key.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Data))
            return false;
        if (obj == this)
            return true;
        return this.getHash() == ((Data) obj).getHash();
    }

    public int getHash() {
        return hash;
    }

    public String getValue() {
        return value;
    }
}