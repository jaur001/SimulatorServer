package backend.model.threads;

import backend.model.client.Client;

import java.util.List;

public class BudgetRestartThread extends Thread{

    public static void restartBudgets(List<Client> clientList){
        clientList.parallelStream()
                .forEach(BudgetRestartThread::restartBudget);
    }

    private static void restartBudget(Client client){
        client.getRoutineList().restartBudget();
    }



}
