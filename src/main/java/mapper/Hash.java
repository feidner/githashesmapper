package mapper;

public class Hash {
    private String hash, number;

    public Hash(String key, Long number) {
        this.hash = key;
        this.number = String.valueOf(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
