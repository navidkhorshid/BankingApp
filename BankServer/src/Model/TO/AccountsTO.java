package Model.TO;

public class AccountsTO
{
    private long id;
    private long person_id;
    private String money;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(long person_id)
    {
        this.person_id = person_id;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String money)
    {
        this.money = money;
    }

}
