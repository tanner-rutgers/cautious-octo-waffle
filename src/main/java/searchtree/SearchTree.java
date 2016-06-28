package searchtree;

public class SearchTree implements Searchable {

    private SearchTree left;
    private SearchTree right;

    private Integer threshold;
    private String value;

    public SearchTree(String val) {
        this.value = val;
    }

    public SearchTree(Integer threshold, SearchTree left, SearchTree right) {
        this.threshold = threshold;
        this.left = left;
        this.right = right;
    }

    public String search(int x) {
        if (value != null) {
            return value;
        }
        String retVal = null;
        if (x <= threshold) {
            if (left != null) {
                retVal = left.search(x);
            }
        } else {
            if (right != null) {
                retVal = right.search(x);
            }
        }
        return retVal;
    }
}