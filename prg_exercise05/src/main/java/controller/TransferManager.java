package controller;

import model.Transaction;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferManager {
    private LinkedList<Transaction> transactions;
    private AtomicInteger volumn; // multi-process safe variable

    public TransferManager(Integer volume) {
        this.volumn = new AtomicInteger(volume);
        transactions=new LinkedList<>();
    }

    /**
     * Transfer flight to another hanger
     * @param source
     * @param destination
     * @param amount
     * @return
     */
    public Transaction addAndGet(String source, String destination, Integer amount){
        // check if the transferred amount beyond the fix volume
        if (amount<volumn.get()){
            Transaction transaction=new Transaction(source,destination,amount,LocalDateTime.now());
            volumn.addAndGet(amount);
            transactions.addLast(transaction);
            return transaction;
        }
        return null;
    }




}
