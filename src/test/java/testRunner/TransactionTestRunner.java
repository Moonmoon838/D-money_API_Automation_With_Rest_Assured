package testRunner;

import api.Transaction;
import api.User;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import model.TransactionModel;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner extends Setup {

    Transaction trnx;

    public TransactionTestRunner() throws IOException {
        initConfig();
        trnx = new Transaction(properties.getProperty("baseUrl"));
    }

    @Test(priority = 1, description = "Deposit Money to Agent Successfully")
    public void depositToAgent(){
        TransactionModel model = new TransactionModel();
        model.setFrom_account("SYSTEM");
        model.setTo_account(properties.getProperty("agent_phone_number"));
        model.setAmount(2000);

        JsonPath jsonPath =trnx.deposit(properties.getProperty("token"), properties.getProperty("partnerKey"), model);
        String messageActual =jsonPath.get("message");

        Assert.assertTrue(messageActual.contains("Deposit successful"));

        Allure.description("Deposit Money to Agent from SYSTEM Successfully");
    }

    @Test(priority = 2, description = "Deposit Money to Customer Successfully")
    public void depositToCustomer(){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(properties.getProperty("agent_phone_number"));
        model.setTo_account(properties.getProperty("customer1_phone_number"));
        model.setAmount(1500);

        JsonPath jsonPath =trnx.deposit(properties.getProperty("token"), properties.getProperty("partnerKey"), model);
        String messageActual =jsonPath.get("message");

        Assert.assertTrue(messageActual.contains("Deposit successful"));

        Allure.description("Deposit Money to Customer1 from Agent Successfully");
    }

    @Test(priority = 3,description = "Withdraw by Customer Successfully")
    public void withdrawByCustomer(){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(properties.getProperty("customer1_phone_number"));
        model.setTo_account(properties.getProperty("agent_phone_number"));
        model.setAmount(500);

        JsonPath jsonPath =trnx.withdraw(properties.getProperty("token"), properties.getProperty("partnerKey"), model);
        String messageActual =jsonPath.get("message");

        Assert.assertTrue(messageActual.contains("Withdraw successful"));

        Allure.description("Withdraw by Customer1 to Agent Successfully");
    }

    @Test(priority = 4,description = "Send Money to Customer Successfully")
    public void sendMoneyToCustomer(){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(properties.getProperty("customer1_phone_number"));
        model.setTo_account(properties.getProperty("customer2_phone_number"));
        model.setAmount(500);

        JsonPath jsonPath =trnx.sendMoney(properties.getProperty("token"), properties.getProperty("partnerKey"), model);
        String messageActual =jsonPath.get("message");

        Assert.assertTrue(messageActual.contains("Send money successful"));

        Allure.description("Send Money to Customer2 by Customer1 Successfully");
    }

    @Test(priority = 5,description = "Payment to Merchant Successfully")
    public void paymentToMerchant(){
        TransactionModel model = new TransactionModel();
        model.setFrom_account(properties.getProperty("customer2_phone_number"));
        model.setTo_account(properties.getProperty("merchant_phone_number"));
        model.setAmount(100);

        JsonPath jsonPath =trnx.payment(properties.getProperty("token"), properties.getProperty("partnerKey"), model);
        String messageActual =jsonPath.get("message");

        Assert.assertTrue(messageActual.contains("Payment successful"));

        Allure.description("Payment to Merchant by Customer2 Successfully");
    }

    @Test(priority = 6,description = "Check Balance of Customer")
    public void checkBalance(){

        String phone =properties.getProperty("customer2_phone_number");
        trnx.checkBalance(properties.getProperty("token"), properties.getProperty("partnerKey"), phone);

        Allure.description("Check Balance of Customer2");
    }

}