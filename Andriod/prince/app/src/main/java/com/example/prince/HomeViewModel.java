package com.example.prince;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    setData service = connection.getRetrofitInstance().create(setData.class);

    //private MutableLiveData<List<CategoryBean>> listMutableLiveData1;
    private MutableLiveData<String> errorResult = new MutableLiveData<>();
    private MutableLiveData<List<SliderBean>> slid;
    public LiveData<String> getErrorResult() {
        return errorResult;
    }

    //Products
    public LiveData<List<SliderBean>> GetSlider(){
        if (slid==null){
            slid=new MutableLiveData<List<SliderBean>>();
            getSlider();

        }
        return slid;
    }
    public void getSlider() {
        Call<List<SliderBean>> call = service.GetSlider();
        call.enqueue(new Callback<List<SliderBean>>() {
            @Override
            public void onResponse(Call<List<SliderBean>> call, Response<List<SliderBean>> response) {
                if (response.isSuccessful()){
                    slid.setValue(response.body());
                }else {
                    errorResult.setValue(response.message());
                }
            }
            @Override
            public void onFailure(Call<List<SliderBean>> call, Throwable t) {
                errorResult.setValue("Try Again");
            }
        });
    }



}
