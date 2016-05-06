package timesofmoney.qnbmerchant.models.adaptermodels;

/**
 * Created by kunalk on 2/16/2016.
 */
public class ListSection implements  Item {

    private String header;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;

        ListSection that = (ListSection) o;

        return getHeader().equals(that.getHeader());

    }

    @Override
    public String toString() {
        return header;
    }

    @Override
    public int hashCode() {
        return getHeader().hashCode();
    }

    @Override
    public boolean isSectioned() {
        return true;
    }
}
