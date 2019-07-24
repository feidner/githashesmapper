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

    Hash(String key, Long number) {
        this.hash = key;
        this.number = String.valueOf(number);
    }

    String getNumber() {
        return number;
    }

    void setNumber(String number) {
        this.number = number;
    }

    String getHash() {
        return hash;
    }

    void setHash(String hash) {
        this.hash = hash;
    }
}