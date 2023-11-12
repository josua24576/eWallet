package com.nanvir.templateapps.module.tapwallet.constants;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.nanvir.templateapps.R;

public class FragmentConstants {

    public final static int HOME_FRAGMENT = 0;
    public final static int HISTORY_FRAGMENT = 1;
    public final static int TRANSACTION_FRAGMENT = 2;
    public final static int STORE_FRAGMENT = 3;
    public final static int ACCOUNT_FRAGMENT = 4;

    public final static int IC_HOME_FRAGMENT = R.drawable.ic_home_button;
    public final static int IC_HISTORY_FRAGMENT = R.drawable.ic_history_button;
    public final static int IC_TRANSACTION_FRAGMENT = R.drawable.ic_arrow_transfer_button;
    public final static int IC_STORE_FRAGMENT = R.drawable.ic_home_button;
    public final static int IC_ACCOUNT_FRAGMENT = R.drawable.ic_home_button;

    public final static String HOME = "Home";
    public final static String HISTORY = "History";
    public final static String TRANSACTION = "Transaction";
    public final static String STORE = "Store";
    public final static String ACCOUNT = "Account";

    public enum Fragments implements Parcelable {
        Home(HOME_FRAGMENT, HOME, true, IC_HOME_FRAGMENT),
        History(HISTORY_FRAGMENT, HISTORY, true, IC_HISTORY_FRAGMENT),
        Transaction(TRANSACTION_FRAGMENT, TRANSACTION, true, IC_TRANSACTION_FRAGMENT),
        Store(STORE_FRAGMENT, STORE, true, IC_STORE_FRAGMENT),
        Account(ACCOUNT_FRAGMENT, ACCOUNT, true, IC_ACCOUNT_FRAGMENT);

        final int id;
        final String title;
        boolean isAvailable;
        final int resId;

        Fragments(int id, String title, boolean isAvailable, int resId) {
            this.id = id;
            this.title = title;
            this.isAvailable = isAvailable;
            this.resId = resId;
        }

        Fragments(Parcel in) {
            id = in.readInt();
            title = in.readString();
            isAvailable = in.readByte() != 0;
            resId = in.readInt();
        }

        public static final Creator<Fragments> CREATOR = new Creator<Fragments>() {
            @Override
            public Fragments createFromParcel(Parcel in) {
                return Fragments.Home;
            }

            @Override
            public Fragments[] newArray(int size) {
                return new Fragments[size];
            }
        };

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public int getResId() {
            return resId;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(title);
            parcel.writeByte((byte) (isAvailable ? 1 : 0));
            parcel.writeInt(resId);
        }
    }
}
