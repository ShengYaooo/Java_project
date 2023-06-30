import java.util.Comparator;

public class LedgerComparator implements Comparator<Ledger>
{
 
    @Override
    public int compare(Ledger L1, Ledger L2) 
    {
        int result = ((Integer)L1.Day).compareTo(L2.Day);
        if (result != 0)
        {
            return result;
        }
        return L1.Category.compareTo(L2.Category);
    }
}