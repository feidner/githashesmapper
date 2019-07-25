package mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hash {
    @Id
    @Column
    private String hash;
    private Long number;

    public Hash(){
    }

    Hash(String key, Long number) {
        this.hash = key;
        this.number = number;
    }

    Long getNumber() {
        return number;
    }

    void setNumber(Long number) {
        this.number = number;
    }

    String getHash() {
        return hash;
    }

    void setHash(String hash) {
        this.hash = hash;
    }
}