import model.Transaction;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class TransferManager {
    private LinkedList<Transaction> transactions;
    private Integer volumn;

    public TransferManager(Integer volume) {
        this.volumn = volume;
        transactions=new LinkedList<>();
    }

    public boolean add(String source, String destination, Integer amount){
        // check if the transferred amount beyond the fix volume
        if (amount<volumn){
            Transaction transaction=new Transaction(source,destination,amount,LocalDateTime.now());
            transactions.add(transaction);
            return false;
        }
        return false;
    }



}
