package mapper;

import java.util.List;

public class HashToNumbers {
    private List<HashToNumber> hashToNumberList;

    public HashToNumbers(List<HashToNumber> hashToNumberList) {
        this.hashToNumberList = hashToNumberList;
    }

    public List<HashToNumber> getHashToNumberList() {
        return hashToNumberList;
    }

    public void setHashToNumberList(List<HashToNumber> hashToNumberList) {
        this.hashToNumberList = hashToNumberList;
    }
}
