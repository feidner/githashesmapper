package mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hash_to_number")
public class HashToNumber {

    private String hash;
    private Long number;

    public HashToNumber(){
    }

    HashToNumber(String key, Long number) {
        this.hash = key;
        this.number = number;
    }

    @Column(
            name="number",
            nullable=false,
            length=20,
            unique = true
    )
    Long getNumber() {
        return number;
    }

    void setNumber(Long number) {
        this.number = number;
    }

    @Id
    @Column(
            name="hash",
            nullable=false,
            length=40
    )
    String getHash() {
        return hash;
    }

    void setHash(String hash) {
        this.hash = hash;
    }
}