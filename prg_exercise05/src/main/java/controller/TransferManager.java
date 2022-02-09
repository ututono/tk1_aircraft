package controller;

import model.Transaction;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferManager {
    private LinkedList<Transaction> transactions;
    private AtomicInteger volume; // multi-process safe variable

    public TransferManager(Integer volume) {
        this.volume = new AtomicInteger(volume);
        transactions=new LinkedList<>();
    }

    /**
     * Transfer flight to another hanger
     * @param source
     * @param destination
     * @param amount
     * @return
     */
    public Transaction transferAndGet(String source, String destination, Integer amount){
        // check if the transferred amount beyond the fix volume
//        if (amount< volume.get()){
//            Transaction transaction=new Transaction(source,destination,amount,LocalDateTime.now());
//            volume.addAndGet(amount);
//            transactions.addLast(transaction);
//            return transaction;
//        }
        return null;
    }

    /**
     * Add new transaction, and increment volume regarding the transaction
     */
    public void add(Transaction transaction){
//        transactions.addLast(transaction);
//        volume.addAndGet(transaction.getAirplanesamount());
    }

    public Integer getVolume(){
//        return volume.get();
        return 0;
    }

    /**
     * put a transaction in queue
     * @param source
     * @param destination
     * @param airplanes
     */
    public void enqueue(String source, String destination, List airplanes){
        Transaction transaction=new Transaction(source,destination,airplanes,LocalDateTime.now());
        transactions.addLast(transaction);
    }

    /**
     *  Retrieves and removes the head (first element) of this list.
     * @return the head of this list, or null if this list is empty
     */
    public Transaction dequeue(){
        return transactions.poll();
    }

    public Integer getSize(){
        return transactions.size();
    }




}
