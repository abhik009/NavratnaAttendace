package com.pci.navratnaattendace.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pci.navratnaattendace.db.User;


public class UsersViewModel extends ViewModel {
    MutableLiveData<User> userdata = new MutableLiveData<>();

    public void toFrag(User user) {
        userdata.setValue(user);
    }

    public LiveData<User> getUserdata() {
        return userdata;
    }
}