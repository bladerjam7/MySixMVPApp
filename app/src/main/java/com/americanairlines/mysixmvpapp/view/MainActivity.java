package com.americanairlines.mysixmvpapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ListView;

import com.americanairlines.mysixmvpapp.R;
import com.americanairlines.mysixmvpapp.model.Shoe;
import com.americanairlines.mysixmvpapp.presenter.ShoePresenter;
import com.americanairlines.mysixmvpapp.presenter.ShoeShopPresenterContract;
import com.americanairlines.mysixmvpapp.view.adapters.ShoeAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.americanairlines.mysixmvpapp.presenter.ShoeShopPresenterContract.*;

public class MainActivity extends AppCompatActivity implements ShoeShopView {

    private ShoeShopPresenter shoePresenter;
    private ShoeAdapter shoeAdapter;
    private ListView lvShoeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvShoeList = findViewById(R.id.lv_shoes);

        shoePresenter = new ShoePresenter(this);

        shoePresenter.insertShoe(new Shoe(8, "Jordan 1 Retro", 59.99, "https://i.ebayimg.com/images/g/-9sAAOSw7AhftqmO/s-l1600.jpg"));
        shoePresenter.insertShoe(new Shoe(8, "Nike Air", 69.99, "https://cdn-images.farfetch-contents.com/13/15/81/89/13158189_14643361_1000.jpg"));
        shoePresenter.insertShoe(new Shoe(8, "Nike flex", 59.99 ,"https://cdn.runrepeat.com/i/nike/32439/nike-men-s-flex-experience-run-8-shoe-black-white-cool-grey-reflective-silver-7-regular-us-black-white-cool-grey-reflective-silver-53a7-600.jpg"));
        shoePresenter.insertShoe(new Shoe(8, "Reebox royal classic", 39.99, "https://assets.reebok.com/images/w_600,f_auto,q_auto/50326299e01748bdbef2aacc01459a21_9366/Reebok_Royal_Classic_Jogger_3.0_Shoes_Black_EF7788_01_standard.jpg"));

        shoePresenter.getAllShoes();
    }

    @Override
    public void displayShoes(List<Shoe> allShoes) {
        Log.d("TAG_X", "All Shoes: " + allShoes.size());

        shoeAdapter = new ShoeAdapter(allShoes);
        lvShoeList.setAdapter(shoeAdapter);
    }

    @Override
    public void displayError(String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog
                        .Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_MySixMVPApp))
                        .setTitle("Database Error")
                        .setMessage(errorMessage)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public void successMessage(String successMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog
                        .Builder(new ContextThemeWrapper(MainActivity.this, R.style.Theme_MySixMVPApp))
                        .setTitle("Database Success")
                        .setMessage(successMessage)
                        .setNegativeButton("Thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }
}