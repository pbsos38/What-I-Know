package com.Prince.paymentintegration.paytm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Prince.paymentintegration.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private String  TAG ="MainActivity";
    private ProgressBar progressBar;
    private EditText txnAmount;
    private String midString ="LFeQkM24927458152903", txnAmountString="10", orderIdString="", txnTokenString="";
    private Button btnPayNow;
    private Integer ActivityRequestCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnPayNow = (Button) findViewById(R.id.txnProcessBtn);
        txnAmount = (EditText) findViewById(R.id.txnAmountId);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min =1000, max= 9999;
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;
        orderIdString =  date+String.valueOf(randomNum);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txnAmountString = txnAmount.getText().toString();
                String errors = "";
                if(orderIdString.equalsIgnoreCase("")){
                    errors ="Enter valid Order ID here\n";
                    Toast.makeText(MainActivity.this, errors, Toast.LENGTH_SHORT).show();

                }else
                if(txnAmountString.equalsIgnoreCase("")){
                    errors ="Enter valid Amount here\n";
                    Toast.makeText(MainActivity.this, errors, Toast.LENGTH_SHORT).show();

                }else{

                    getToken();
                }

            }
        });
    }

    private  void getToken(){
        Log.e(TAG, " get token start");
        progressBar.setVisibility(View.VISIBLE);
        SetDataToDatabase serviceWrapper = Connection.doConnect();
        serviceWrapper.make_payment(orderIdString, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse bean_file_token_paytm, Response response) {
                //Log.d("TAG", " respo "+ response.getBody().toString() );
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(MainActivity.this, ""+bean_file_token_paytm.body, Toast.LENGTH_SHORT).show();
                //JsonObject jsonObject = new JsonObject()
                Toast.makeText(MainActivity.this, ""+bean_file_token_paytm.body.toString(), Toast.LENGTH_SHORT).show();

                String[] c = bean_file_token_paytm.body.toString().split("txnToken=");
                String[] d = c[1].split(", isPromoCodeValid");
                Log.d("TAG",""+d[0]);
                startPaytmPayment(d[0]);

                /*try {
                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonElements = jsonObject.getJSONObject("txnToken");
                    Log.d("TAG",""+jsonElements);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }*/
                /*JSONObject json = (JSONObject) JSONSerializer.toJSON(bean_file_token_paytm.body.toString());
                JsonObject jjson = new JsonObject(bean_file_token_paytm.body.toString());

                JSONObject pilot = json.getJSONObject("pilot");
                String firstName = pilot.getString("firstName");
                String lastName = pilot.getString("lastName");*/

                try {

                    //startPaytmPayment(bean_file_token_paytm.getTxnToken());
/*
                    if (response.isSuccessful() && response.body()!=null){
                        if (response.body().getBody().getTxnToken()!="") {
                            Log.e(TAG, " transaction token : "+response.body().getBody().getTxnToken());
                        }else {
                            Log.e(TAG, " Token status false");
                        }
                }
*/
            }catch (Exception e){
                    Log.e(TAG, " error in Token Res "+e.toString());
                }

            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, " response error "+error.toString());
            }
        });
        /*Call<Token_Res> call = serviceWrapper.getTokenCall("12345", midString, orderIdString, txnAmountString);
        call.enqueue(new Callback<Token_Res>() {
            @Override
            public void onResponse(Call<Token_Res> call, Response<Token_Res> response) {
                Log.e(TAG, " respo "+ response.isSuccessful() );
                progressBar.setVisibility(View.GONE);
                try {

                    if (response.isSuccessful() && response.body()!=null){
                        if (response.body().getBody().getTxnToken()!="") {
                            Log.e(TAG, " transaction token : "+response.body().getBody().getTxnToken());
                            startPaytmPayment(response.body().getBody().getTxnToken());
                        }else {
                            Log.e(TAG, " Token status false");
                        }
                    }
                }catch (Exception e){
                    Log.e(TAG, " error in Token Res "+e.toString());
                }
            }

            @Override
            public void onFailure(Call<Token_Res> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, " response error "+t.toString());
            }
        });*/

    }


    public void startPaytmPayment (String token){

        txnTokenString = token;
        // for test mode use it
        // String host = "https://securegw-stage.paytm.in/";
        // for production mode use it
        String host = "https://securegw-stage.paytm.in/";
        //String orderDetails = "MID: " + midString + ", OrderId: " + orderIdString + ", TxnToken: " + txnTokenString+ ", Amount: " + txnAmountString;
        //Log.e(TAG, "order details "+ orderDetails);

        String callBackUrl = host + "theia/paytmCallback?ORDER_ID="+orderIdString;
        Log.e(TAG, " callback URL "+callBackUrl);
        PaytmOrder paytmOrder = new PaytmOrder(orderIdString, "LFeQkM24927458152903", txnTokenString, "1.00", callBackUrl);
        TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback(){
            @Override
            public void onTransactionResponse(Bundle bundle) {
                Log.e(TAG, "Response (onTransactionResponse) : "+bundle.toString());
            }

            @Override
            public void networkNotAvailable() {
                Log.e(TAG, "network not available ");
            }

            @Override
            public void onErrorProceed(String s) {
                Log.e(TAG, " onErrorProcess "+s.toString());
            }

            @Override
            public void clientAuthenticationFailed(String s) {
                Log.e(TAG, "Clientauth "+s);
            }

            @Override
            public void someUIErrorOccurred(String s) {
                Log.e(TAG, " UI error "+s);
            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {
                Log.e(TAG, " error loading web "+s+"--"+s1);
            }

            @Override
            public void onBackPressedCancelTransaction() {
                Log.e(TAG, "backPress ");
            }

            @Override
            public void onTransactionCancel(String s, Bundle bundle) {
                Log.e(TAG, " transaction cancel "+s);
            }
        });

        transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage");
        transactionManager.startTransaction(this, ActivityRequestCode);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG ," result code "+resultCode);
        // -1 means successful  // 0 means failed
        // one error is - nativeSdkForMerchantMessage : networkError
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCode && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    Log.e(TAG, key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
                }
            }
            Log.e(TAG, " data "+  data.getStringExtra("nativeSdkForMerchantMessage"));
            Log.e(TAG, " data response - "+data.getStringExtra("response"));
/*
 data response - {"BANKNAME":"WALLET","BANKTXNID":"1394221115",
 "CHECKSUMHASH":"7jRCFIk6eRmrep+IhnmQrlrL43KSCSXrmM+VHP5pH0ekXaaxjt3MEgd1N9mLtWyu4VwpWexHOILCTAhybOo5EVDmAEV33rg2VAS/p0PXdk\u003d",
 "CURRENCY":"INR","GATEWAYNAME":"WALLET","MID":"EAcP3138556","ORDERID":"100620202152",
 "PAYMENTMODE":"PPI","RESPCODE":"01","RESPMSG":"Txn Success","STATUS":"TXN_SUCCESS",
 "TXNAMOUNT":"2.00","TXNDATE":"2020-06-10 16:57:45.0","TXNID":"2020061011121280011018328631290118"}
  */
            Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage")
                    + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();
        }else{
            Log.e(TAG, " payment failed");
        }
    }

}