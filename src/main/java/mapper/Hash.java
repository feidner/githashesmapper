package mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hash {
    @Id
    @Column
    private String hash;
    private String number;

    public Hash(){

    }

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
