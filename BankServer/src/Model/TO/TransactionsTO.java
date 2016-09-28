package Model.TO;

public class TransactionsTO
{
    private long id;
    private long fa_id;
    private long sa_id;
    private String amount;
    private String time;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getFa_id()
    {
        return fa_id;
    }

    public void setFa_id(long fa_id)
    {
        this.fa_id = fa_id;
    }

    public long getSa_id()
    {
        return sa_id;
    }

    public void setSa_id(long sa_id)
    {
        this.sa_id = sa_id;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

}
