package mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hash")
public class Hash {
    @Id
    @Column(
        name="hash",
        nullable=false,
        length=40
    )
    private String hash;

    @Column(
        name="number",
        nullable=false,
        length=20,
        unique = true
    )
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