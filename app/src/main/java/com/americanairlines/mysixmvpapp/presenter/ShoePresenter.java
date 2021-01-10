package com.americanairlines.mysixmvpapp.presenter;

import com.americanairlines.mysixmvpapp.model.Shoe;
import com.americanairlines.mysixmvpapp.model.db.ShoeDatabaesHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoePresenter implements ShoeShopPresenterContract.ShoeShopPresenter{

    private ShoeShopPresenterContract.ShoeShopView shoeShopView;
    private ShoeDatabaesHelper shoeDatabaesHelper;

    public ShoePresenter(ShoeShopPresenterContract.ShoeShopView shoeShopView) {
        this.shoeShopView = shoeShopView;
        shoeDatabaesHelper = new ShoeDatabaesHelper(shoeShopView.getContext());
    }

    @Override
    public void getAllShoes() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    shoeShopView.displayShoes(shoeDatabaesHelper.getAllShoe());
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();


        // When I get all shoes from the database... We don't want to do this
        // because it may block the main thread
        //shoeShopView.displayShoes(new ArrayList<>());
    }

    @Override
    public void insertShoe(Shoe newShoe) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    shoeDatabaesHelper.insertNewShoeIntoDatabase(newShoe);
                    shoeShopView.successMessage(newShoe.getShoeModel() + " Inserted!!");
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();

    }

    @Override
    public void deleteShoe(Shoe deleteShoe) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    shoeDatabaesHelper.deleteShoeFromDatabase(deleteShoe);
                    shoeShopView.successMessage(deleteShoe.getShoeModel() + " Deleted!!");
                } catch (Exception e) {
                    e.printStackTrace();
                    shoeShopView.displayError(e.getMessage());
                }
            }
        }.start();

    }
}
